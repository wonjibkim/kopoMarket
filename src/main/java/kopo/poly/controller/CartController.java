package kopo.poly.controller;

import kopo.poly.dto.*;
import kopo.poly.service.ICartService;
import kopo.poly.service.IMapService;
import kopo.poly.service.IPayService;
import kopo.poly.util.CmmUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "Cart")
public class CartController {

    @Resource(name = "CartService")
    private ICartService cartService;

    @Resource(name = "PayService")
    private IPayService kakaopay;


    @PostMapping(value = "cart_add") // 상품 카트 추가
    public String cart_add(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(getClass().getName() + "cart_add start");

        String url = "/market/index";
        String msg = ""; // 상황에 따라 alert창 띄우기
        CartDTO cDTO = null;

        try {
            String food_num = CmmUtil.nvl(request.getParameter("food_num"));
            String cart_count = CmmUtil.nvl(request.getParameter("cart_count"));
            String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));

            log.info("food_num" + food_num);
            log.info("cart_count" + cart_count);
            log.info("user_seq" + user_seq);


            cDTO = new CartDTO();

            cDTO.setUser_seq(Integer.parseInt(user_seq));
            cDTO.setCart_count(Integer.parseInt(cart_count));
            cDTO.setFood_num(Integer.parseInt(food_num));

            int res = cartService.InsertFoodInCart(cDTO);
            log.info("카트 결과 res" + res);

            if (res == 1) {
                msg = "카트 담기에 성공하였습니다";


            } else if (res == 2) {
                msg = "이미 담겨져 있는 품목입니다";
            } else {
                msg = "오류로 인해서 카트에 담기에 실패하였습니다";
            }

        } catch (Exception e) { // 실패시 나오는 에러메세지
            msg = "실패하였습니다. " + e;
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(getClass().getName() + "cart_add end");
            log.info("url : " + url);
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

            cDTO = null;
        }
        return "/redirect";
    }


    @GetMapping(value = "cartList") // 카트 보여주기
    public String cartList(HttpSession session, ModelMap model) throws Exception {

        log.info(getClass().getName() + "cartList start");

        String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
        log.info("user_seq" + user_seq);


        Cart_foodDTO rDTO = new Cart_foodDTO();
        rDTO.setUser_seq(Integer.parseInt(user_seq));

        List<Cart_foodDTO> rList = cartService.cartList(rDTO);

        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }
        log.info("반환값 사이즈 " + Integer.toString(rList.size()));

        log.info(getClass().getName() + "cartList end");

        model.addAttribute("rList", rList);
        return "/market/CartList";

    }


    @PostMapping(value = "cartDelete")
    @ResponseBody
    public List<Cart_foodDTO> cartDelete(HttpServletRequest request, ModelMap model, @RequestParam(value = "cart_num[]") List<String> cart_num,
                                         HttpSession session) throws Exception { // 배열로 받고


        log.info(getClass().getName() + "cartDelete start");

//            String cart_num [] = request.getParameterValues("chk_lang");

        log.info("cart_num length : " + cart_num.size()); //배열 잘 들어갔나 확인
        for (String num : cart_num) {
            log.info("cart_num value : " + num);
        }

//            List<CartDTO>aList = new ArrayList<>();

//        for (int i =0; i<cart_num.size(); i++){ 리스트 안에 dto에 값 하나 씩 넣을려고 했지만 실패
//
//            CartDTO a = new CartDTO();
//            a.setCart_num(Integer.parseInt(cart_num.get(i)));
//            aList.add(a);
//            a = null;
//
//        }

        cartService.cartDelete(cart_num);

        String user_seq = CmmUtil.nvl((String) session.getAttribute("seq")); // 세션 받아서 변수에 넣기
        log.info("user_seq" + user_seq);

        Cart_foodDTO rDTO = new Cart_foodDTO();
        rDTO.setUser_seq(Integer.parseInt(user_seq));

        List<Cart_foodDTO> rList = cartService.cartList(rDTO);

        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        log.info("반환값 사이즈 " + Integer.toString(rList.size()));

        log.info(getClass().getName() + "cartDelete end");


        return rList;

    }

    @GetMapping(value = "purchase")
    public String purchase(HttpSession session, ModelMap model) throws Exception {

        log.info(getClass().getName() + "purchase start");
        String msg = "";
        String url = "";

        try {
            String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
            log.info("user_seq" + user_seq);

            CartDTO rDTO = new CartDTO();
            rDTO.setUser_seq(Integer.parseInt(user_seq));


            List<kPayDTO> pDTO = cartService.purchase(rDTO); // payDTO에 카카오페이 결제에 필요한 정보를 리턴
            kakaopay.kakaoPayReady();

            msg = "결제하기 성공하였습니다";


        } catch (Exception e) { // 실패시 나오는 에러메세지
            msg = "실패하였습니다. " + e;
            log.info(e.toString());
            e.printStackTrace();

            url = "";
            msg = "결제하기에 실패하였습니다";

        } finally {
            log.info(getClass().getName() + "cart_add end");
            log.info("url : " + url);

            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        return "/redirect";
    }








}
