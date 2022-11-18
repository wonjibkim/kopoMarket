package kopo.poly.service;

import kopo.poly.dto.CartDTO;

public interface ICartService {

    int InsertFoodInCart(CartDTO cDTO) throws Exception; // 카트에 넣기
}
