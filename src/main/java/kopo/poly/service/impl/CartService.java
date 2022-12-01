package kopo.poly.service.impl;


import kopo.poly.dto.*;
import kopo.poly.persistance.mapper.ICartMapper;
import kopo.poly.persistance.mapper.IMarketMapper;
import kopo.poly.service.ICartService;
import kopo.poly.service.IMapService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("CartService")
public class CartService implements ICartService {

    private final ICartMapper cartMapper;

    @Override
    @Transactional
    public int InsertFoodInCart(CartDTO cDTO) throws Exception {

        int res = 0;

        if (cDTO == null) {
            cDTO = new CartDTO();
            log.info("cDTO가 널이어서 메모리에 강제로 올림");
        } //널처리

        CartDTO rDTO = cartMapper.SelectCountInCart(cDTO); // 중복체크

        if (rDTO == null) {
            rDTO = new CartDTO();
        }

        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
            res = 2;
        } else {
            log.info("user_seq : " + cDTO.getUser_seq());
            int success = cartMapper.InsertFoodInCart(cDTO);

            if (success > 0) {
                res = 1;
            } else {
                res = 0;
            }
        }


        return res;
    }

    @Override
    public List<Cart_foodDTO> cartList(Cart_foodDTO rDTO) {
        List<Cart_foodDTO> rList = cartMapper.cartList(rDTO);

        if (rList == null) {
            System.out.println("service에서 반환값없음");
        }
        log.info("반환값 사이즈 " + Integer.toString(rList.size()));

        return rList;
    }

    @Override
    @Transactional
    public void cartDelete(List<String> cart_num) {

        log.info(getClass().getName() + "cartDelete Start");

        cartMapper.cartDelete(cart_num);
        log.info(getClass().getName() + "cartDelete End");
    }


    @Override
    public List<kPayDTO> purchase(CartDTO rDTO) {


        log.info(getClass().getName() + "purchase service start");
        if (rDTO == null) {
            rDTO = new CartDTO();
            log.info("cDTO가 널이어서 메모리에 강제로 올림");
        }


        List<PayDTO> qList = cartMapper.CartFoodSelect(rDTO); // cart테이블에 있는 정보와 연관된 정보를 가져옴

        if (qList == null) {
            log.info("rList에 값이 안 들어옴");
        }

        for (int i = 0; i < qList.size(); i++) {

            PayDTO pDTO = qList.get(i);

            if (Integer.parseInt(pDTO.getP_sell()) >= pDTO.getCart_count()) {
                continue;
            } else {
                log.info("수량초과 에러처리 요망");
            }

        }


        int res = 0;


        List<CartDTO> rList = cartMapper.getcartPurchase(rDTO); // 카트의 food_num과 cart_count를 list에 담아서 가져옴
        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어옴");
        } else {
            log.info("list에 값이 들어옴");
        }


            for (int i = 0; i < rList.size(); i++) {   // 재고수량 변경
                CartDTO eDTO = rList.get(i);
                res = cartMapper.cartPurchaseUpdate(eDTO);
            }
            if (res == 1) {
                log.info("값이 변경됨");
            }

            List<PayDTO> pList = cartMapper.getCartPay(rDTO);
            if (pList == null) {
                pList = new ArrayList<>();
                log.info("pList에 값이 없음");
            } else {
                log.info("payDTO에 값이 들어옴");
            }
            for (PayDTO tDTO : pList) {
                log.info("user_seq " + Integer.toString(tDTO.getUser_seq()));
                log.info("food_num" + Integer.toString(tDTO.getFood_num()));
                log.info("cart_count" + Integer.toString(tDTO.getCart_count()));
                log.info("p_price" + tDTO.getP_price());
            }


            res = 0;
            for (int i = 0; i < pList.size(); i++) {
                PayDTO eDTO = pList.get(i);
                res = cartMapper.InsertPay(eDTO);
            }

            if (res == 1) {
            log.info("pay테이블에 값이 들어감");
            }


            cartMapper.cartAllDelete(rDTO); //cart 테이블 삭제





            log.info(getClass().getName() + "purchase service emd");


            return null;
        }


    }
