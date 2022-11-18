package kopo.poly.persistance.mapper;

import kopo.poly.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartMapper {

    int InsertFoodInCart(CartDTO pDTO) throws Exception; // 카트에 넣기

    CartDTO SelectCountInCart(CartDTO rDTO) throws Exception; //넣기 전 중복체크
}
