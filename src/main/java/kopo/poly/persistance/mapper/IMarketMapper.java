package kopo.poly.persistance.mapper;


import kopo.poly.dto.CartDTO;
import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IMarketMapper {



    void FoodInsert(FoodDTO fDTO)throws Exception; // 식자재 등록

    List<FoodDTO> FoodList(FoodDTO fDTO);// 식자재 전체리스트

    FoodDTO FoodEditInfo(FoodDTO pDTO) throws Exception;


    void foodUp(FoodDTO fDTO)throws Exception;

    void FoodDelete(FoodDTO pDTO)throws Exception;

    List<FoodDTO> FoodListShelf(FoodDTO fDTO); //유통기한 지난 것들 게시판
    void FoodDeleteShelf(FoodDTO mDTO);

    List<MarketInfoDTO> MartMap();

//    void maketInsertInfo(List<MarketPasingDTO> mpList);
    void maketInsertInfo(MarketPasingDTO mpDTO);

    void marketapide(); // api 가져오기전에 삭제하기

    List<MarketPasingDTO>pasingMap();


    int InsertFoodInCart(CartDTO pDTO) throws Exception; // 카트에 넣기

    CartDTO SelectCountInCart(CartDTO rDTO) throws Exception; //넣기 전 중복체크


    void update_barcode(FoodDTO fDTO); // 바코드로 수량뺴기 쿼리

    List<FoodDTO> FoodListZero(FoodDTO fDTO);

    void Foodzerodel(FoodDTO mDTO);
}
