package kopo.poly.service;

import kopo.poly.dto.CartDTO;
import kopo.poly.dto.Cart_foodDTO;
import kopo.poly.dto.PayDTO;
import kopo.poly.dto.kPayDTO;

import java.util.List;

public interface ICartService {

    int InsertFoodInCart(CartDTO cDTO) throws Exception; // 카트에 넣기

    List<Cart_foodDTO> cartList(Cart_foodDTO rDTO);

    void cartDelete(List<String> cart_num);


    List<kPayDTO> purchase(CartDTO rDTO);
}
