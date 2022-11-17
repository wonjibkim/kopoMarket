package kopo.poly.persistance.mapper;


import kopo.poly.dto.CartDTO;
import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IMarketMapper {

    void FoodInsert(FoodDTO fDTO)throws Exception;

    List<FoodDTO> FoodList();

    FoodDTO FoodEditInfo(FoodDTO pDTO) throws Exception;


    void foodUp(FoodDTO fDTO)throws Exception;

    void FoodDelete(FoodDTO pDTO)throws Exception;

    List<FoodDTO> FoodListShelf();
    void FoodDeleteShelf();

    List<MarketInfoDTO> MartMap();

//    void maketInsertInfo(List<MarketPasingDTO> mpList);
    void maketInsertInfo(MarketPasingDTO mpDTO);

    void marketapide(); // api 가져오기전에 삭제하기

    List<MarketPasingDTO>pasingMap();


    int InsertFoodInCart(CartDTO pDTO) throws Exception; // 가입하기

    CartDTO SelectCountInCart(CartDTO rDTO) throws Exception; //넣기 전 중복체크
}
