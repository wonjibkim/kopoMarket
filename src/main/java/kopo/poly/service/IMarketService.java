package kopo.poly.service;


import kopo.poly.dto.*;

import java.util.List;

public interface IMarketService {

    void FoodInsert(FoodDTO fDTO) throws Exception; // 식자재등록

    List<FoodDTO> FoodList(FoodDTO fDTO); // 식자재 전체 리스트 보여주기

    FoodDTO FoodEditInfo(FoodDTO pDTO) throws Exception;

    void foodUp(FoodDTO fDTO)throws Exception;

    void FoodDelete(FoodDTO pDTO) throws Exception;

    List<FoodDTO>FoodListShelf(FoodDTO fDTO);//유통기한 지난 것 게시판

    void FoodDeleteShelf(FoodDTO mDTO)throws Exception;

    List<MarketInfoDTO>MartMap();

    List<MarketPasingDTO>pasingMap();

    void maketInsertInfo() throws Exception; // api 가져오기 service


    int InsertFoodInCart(CartDTO cDTO) throws Exception; // 카트에 넣기


    void update_barcode(FoodDTO fDTO); // 바코드 값 인식 insert

    List<FoodDTO> FoodListZero(FoodDTO fDTO); // 유통기한 제로 게시판

    void Foodzerodel(FoodDTO mDTO);
}
