package kopo.poly.service;


import kopo.poly.dto.*;

import java.util.List;

public interface IMarketService {

    void FoodInsert(FoodDTO fDTO) throws Exception;

    List<FoodDTO> FoodList();

    FoodDTO FoodEditInfo(FoodDTO pDTO) throws Exception;

    void foodUp(FoodDTO fDTO)throws Exception;

    void FoodDelete(FoodDTO pDTO) throws Exception;

    List<FoodDTO>FoodListShelf();

    void FoodDeleteShelf()throws Exception;

    List<MarketInfoDTO>MartMap();

    List<MarketPasingDTO>pasingMap();

    void maketInsertInfo() throws Exception; // api 가져오기 service


    int InsertFoodInCart(CartDTO cDTO) throws Exception; // 카트에 넣기


    void update_barcode(FoodDTO fDTO); // 바코드 값 인식 insert
}
