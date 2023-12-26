package kopo.poly.service.impl;


import kopo.poly.dto.priceDTO;
import kopo.poly.persistance.mapper.IPrcieMapper;
import kopo.poly.service.IPriceService;
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
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service(value = "PriceService")
public class PriceService implements IPriceService {

   private final IPrcieMapper prcieMapper;

    @Override
    @Transactional
//    @Scheduled(cron = "00 00 18 * *  MON-FRI")
    @Scheduled(cron = "55 22 18 * *  MON-FRI")

    public void priceInSertInfo() throws Exception {

        LocalDate now = LocalDate.now();
        String date = (now +"").replaceAll("-","");
        log.info( "오늘 날짜"+date);


        log.info(getClass().getName() + " 파싱 시작");
        String key = "41674d4fa65ca2542649359e21dfbc3e623de69b23884caaf79202406ce3a33a";

        log.info(getClass().getName()+ "try 들어오기 전 들어옴");
        try{
            /**
                JPA로 수정바람
              */
            prcieMapper.marketapide(); // 새로 받을 떄 지우기
            log.info(getClass().getName()+ "try 들어옴");

            // parsing할 url 지정(API 키 포함해서)
            String url = "http://211.237.50.150:7080/openapi/"+key+"/xml/Grid_20141225000000000163_1/1/1000?EXAMIN_DE=20150508";

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // 제일 첫번째 태그
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("row");
            log.info(getClass().getName()+ "for문 전");
            // 파싱할 tag

            log.info(nList.getLength()+"크기");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;

                try {
                    log.info("for문 들어옴");


                    priceDTO mpDTO = new priceDTO();
                    mpDTO.setPrdlst_nm(XmlPasingUtil.getTagValue("PRDLST_NM", eElement)); // 품목명
                    mpDTO.setAmt(XmlPasingUtil.getTagValue("AMT", eElement)); //가격
                    mpDTO.setArea_nm(XmlPasingUtil.getTagValue("AREA_NM", eElement)); // 지역명
                    mpDTO.setExamin_unit(XmlPasingUtil.getTagValue("EXAMIN_UNIT", eElement)); // 조사단위

                    log.info(mpDTO.getArea_nm());

                    /**
                        JPA 로 수정 바람
                     */

                    int res = prcieMapper.priceInSert(mpDTO);
                    log.info( "가격 정보 업데이트 완료 " + res);

                    //DB에 넣기

                }catch (NullPointerException e) {
                    continue;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<priceDTO> getprice() {

         /**
                        JPA 로 수정 바람
                     */

        List<priceDTO> rLsit =  prcieMapper.getprice();

        return rLsit;
    }


}
