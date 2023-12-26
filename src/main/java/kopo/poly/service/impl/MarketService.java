package kopo.poly.service.impl;


import kopo.poly.dto.*;
import kopo.poly.persistance.mapper.IMarketMapper;
import kopo.poly.service.IMarketService;
import kopo.poly.util.CmmUtil;
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
import java.util.ArrayList;
import java.util.List;

@Service("MarketService")
@Slf4j
@RequiredArgsConstructor
public class MarketService implements IMarketService {

        private final IMarketMapper marketMapper;


        @Transactional
        @Override // 식자재 등록
        public void FoodInsert(FoodDTO fDTO) throws Exception {

                log.info(this.getClass().getName() + "InsertFood start!");
                log.info("name : "+fDTO.getP_name());
                marketMapper.FoodInsert(fDTO);

                log.info(this.getClass().getName() + "InsertFood end");

        }

        @Override // 식자재 전체리스트
        public List<FoodDTO> FoodList(FoodDTO fDTO) {

                List<FoodDTO>rList =marketMapper.FoodList(fDTO);

                return rList;
        }

        @Override // 상세페이지 보여주기
        public FoodDTO FoodEditInfo(FoodDTO pDTO) throws Exception {

                log.info(this.getClass().getName() + "getFoodinfo start!");



                return marketMapper.FoodEditInfo(pDTO);
        }

        @Override // 수정
        @Transactional
        public void foodUp(FoodDTO fDTO) throws Exception {

                log.info(this.getClass().getName() + "foodUp start!");
                log.info("name : "+fDTO.getP_name());
                marketMapper.foodUp(fDTO);

                log.info(this.getClass().getName() + "foodUp end");

        }

        @Override// 삭제
        @Transactional
        public void FoodDelete(FoodDTO pDTO) throws Exception {

                log.info(getClass().getName()+ "FoodDelete Start");

                marketMapper.FoodDelete(pDTO);
                log.info(getClass().getName()+ "FoodDelete End");

        }

        @Override
        public List<FoodDTO> FoodListShelf(FoodDTO fDTO) { //유통기한 지난 것 게시판
                log.info( "SEQ 번호 " +fDTO.getMarket_seq());
                List<FoodDTO>rList =marketMapper.FoodListShelf(fDTO);


                return rList;
        }

        @Override // 삭제하기
        @Transactional
        public void FoodDeleteShelf(FoodDTO mDTO) {

                log.info(getClass().getName()+ "FoodDeleteShelf Start");
                marketMapper.FoodDeleteShelf(mDTO);

                log.info( "SEQ 번호 " +mDTO.getMarket_seq());
                log.info(getClass().getName()+ "FoodDeleteShelf End");
        }

        @Override
        public List<MarketInfoDTO> MartMap() {

                List<MarketInfoDTO>mList =marketMapper. MartMap();

                return mList;
        }

        @Override
        public List<MarketPasingDTO> pasingMap() {
                List<MarketPasingDTO>pList = marketMapper.pasingMap();

                return pList;
        }

        @Override
        @Transactional
        @Scheduled(cron = "30 08 00 * * *")
        public void maketInsertInfo() throws Exception {
                String key = "b35fa576b24c4a45bbdbb725be7de1b9";

                try{
                        marketMapper.marketapide(); // 새로 받을 떄 지우기
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
                                        marketMapper.maketInsertInfo(mpDTO);
                                }catch (NullPointerException e) {
                                        continue;
                                }
                        }

                } catch (Exception e){
                        e.printStackTrace();
                }
        }

        @Override
        @Transactional
        public int InsertFoodInCart(CartDTO cDTO) throws Exception {

                int res = 0;

                if(cDTO == null){
                        cDTO = new CartDTO();
                        log.info("cDTO가 널이어서 메모리에 강제로 올림");
                } //널처리

                CartDTO rDTO = marketMapper.SelectCountInCart(cDTO); // 중복체크

                if (rDTO == null){
                        rDTO = new CartDTO();
                }

                if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
                        res = 2;
                }else {
                        log.info("user_seq : "+cDTO.getUser_seq());
                        int success = marketMapper.InsertFoodInCart(cDTO);

                        if(success > 0){
                                res =1;
                        }else {
                                res = 0;
                        }
                }


                return res;
        }

        @Override //바코드 수량뺴기 쿼리
        @Transactional
        public void update_barcode(FoodDTO fDTO) {
                log.info(getClass().getName() + "BarcodeInsert start");

                marketMapper.update_barcode(fDTO);


                log.info(getClass().getName() + "BarcodeInsert end");
        }

        @Override
        public List<FoodDTO> FoodListZero(FoodDTO fDTO) {

                List<FoodDTO>rList =marketMapper.FoodListZero(fDTO);

                return rList;
        }

        @Override
        public void Foodzerodel(FoodDTO mDTO) {
                log.info(getClass().getName()+ "FoodDeleteShelf Start");
                marketMapper.Foodzerodel(mDTO);

                log.info( "SEQ 번호 " +mDTO.getMarket_seq());
                log.info(getClass().getName()+ "FoodDeleteShelf End");
        }


}

