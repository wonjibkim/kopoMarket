package kopo.poly.persistance.mapper;

import kopo.poly.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICartMapper {




    int InsertFoodInCart(CartDTO pDTO) throws Exception; // 카트에 넣기

    CartDTO SelectCountInCart(CartDTO rDTO) throws Exception; //넣기 전 중복체크

    List<Cart_foodDTO> cartList(Cart_foodDTO rDTO);

    void cartDelete(List<String> cart_num);


    List<PayDTO> CartFoodSelect(CartDTO rDTO);

    List<CartDTO> getcartPurchase(CartDTO rDTO);

    int cartPurchaseUpdate(CartDTO eDTO);


    List<PayDTO> getCartPay(CartDTO rDTO); //결제에 필요한 정보를 담을 테이블


    int InsertPay(PayDTO eDTO);


    void cartAllDelete(CartDTO rDTO);

    kPayDTO getPaySelect(CartDTO rDTO);


    String getSumprice(CartDTO rDTO);

    String getCart_count(CartDTO rDTO);


    FoodDTO FoodCartbar(FoodDTO fDTO);

    int cartCountadd(CartDTO cDTO);

    int cartCountdel(CartDTO cDTO);


}
