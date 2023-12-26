package kopo.poly.service.impl;


import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;
import kopo.poly.persistance.mapper.IMapMapper;
import kopo.poly.service.IMapService;
import kopo.poly.util.XmlPasingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("MapService")
public class MapService implements IMapService {

    private final IMapMapper mapMapper;

    @Override
    public List<MarketInfoDTO> MartMap() {

        List<MarketInfoDTO>mList =mapMapper. MartMap();

        return mList;
    }

    @Override
    public List<MarketPasingDTO> pasingMap() {
        List<MarketPasingDTO>pList = mapMapper.pasingMap();

        return pList;
    }

    @Override
    @Transactional
    @Scheduled(cron = "00 12 18 * * *")
    public void maketInsertInfo() throws Exception {
        String key = "b35fa576b24c4a45bbdbb725be7de1b9";

        try{
            mapMapper.marketapide(); // 새로 받을 떄 지우기
            // parsing할 url 지정(API 키 포함해서)
            String url = "https://openapi.gg.go.kr/Distrbspecltysalebiz?key="+key+"&pIndex=1&pSize=1000";

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // 제일 첫번째 태그
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("row");

            // 파싱할 tag
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;

                try {
                    MarketPasingDTO mpDTO = new MarketPasingDTO();
                    mpDTO.setRefine_wgs84_lat(XmlPasingUtil.getTagValue("REFINE_WGS84_LAT", eElement));
                    mpDTO.setRefine_wgs84_logt(XmlPasingUtil.getTagValue("REFINE_WGS84_LOGT", eElement));
                    mpDTO.setBizplc_nm(XmlPasingUtil.getTagValue("BIZPLC_NM", eElement));
                    mpDTO.setLicensg_de(XmlPasingUtil.getTagValue("LICENSG_DE", eElement));
                    mpDTO.setRefine_roadnm_addr(XmlPasingUtil.getTagValue("REFINE_ROADNM_ADDR", eElement));
                    mapMapper.maketInsertInfo(mpDTO);
                }catch (NullPointerException e) {
                    continue;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
