package kopo.poly.controller;

import kopo.poly.dto.CartDTO;
import kopo.poly.dto.Cart_foodDTO;
import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.kPayDTO;
import kopo.poly.service.ICartService;
import kopo.poly.service.IPayService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String url = "/Cart/cartList";

        try {
            String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
            log.info("user_seq" + user_seq);

            CartDTO rDTO = new CartDTO();
            rDTO.setUser_seq(Integer.parseInt(user_seq));
            kPayDTO gDTO = cartService.purchase(rDTO); // payDTO에 카카오페이 결제에 필요한 정보를 리턴

            if (gDTO == null){
                msg = "재고수량 보다 구매수량이 많습니다 장바구니를 확인해주세요";
            }else {
                log.info("구입 진행");
                return "redirect:" + kakaopay.kakaoPayReady(gDTO);
            }



        } catch (Exception e) { // 실패시 나오는 에러메세지
            msg = "실패하였습니다. " + e;
            log.info(e.toString());
            e.printStackTrace();

            url = "/Cart/cartList";
            msg = "결제하기에 실패하였습니다";

        } finally {
            log.info(getClass().getName() + "cart_add end");
            log.info("url : " + url);

            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        return "/redirect";
    }


    @GetMapping(value = "BarCodeCart") // 바코드로 장바구니 추가
    public String pr()throws Exception{

        log.info(getClass().getName()+"pr start");
        log.info(getClass().getName()+"pr start");
        return "/market/BarCodeCart";
    }

    @PostMapping(value = "BarCodeCart_start") // 상품 카트 추가
    public String BarCodeCart_start(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        log.info(getClass().getName() + "BarCodeCart_start start");

        String url = "/Cart/BarCodeCart";
        String msg = ""; // 상황에 따라 alert창 띄우기
        CartDTO cDTO = null;

        try {
            String P_barcode = CmmUtil.nvl(request.getParameter("p_barcode"));
            String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));

            log.info("P_barcode" + P_barcode);
            log.info("user_seq" + user_seq);

            FoodDTO fDTO = new FoodDTO();
            fDTO.setP_barcode(P_barcode);
            fDTO = cartService.FoodCartbar(fDTO);

            String food_num = fDTO.getP_num();

            log.info("들어간 p_num" + food_num);
            cDTO = new CartDTO();

            cDTO.setUser_seq(Integer.parseInt(user_seq));
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
            log.info(getClass().getName() + "BarCodeCart_start end");
            log.info("url : " + url);

            url = "/Cart/BarCodeCart";
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

            cDTO = null;
        }
        return "/redirect";
    }

    @PostMapping(value = "update_cart")
    @ResponseBody
    public Map<String, Integer> update_cart(HttpServletRequest request , HttpSession session, ModelMap model) throws Exception {
        log.info(getClass().getName() + "update_cart start");

        String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
        String food_num = CmmUtil.nvl(request.getParameter("p_num"));
        log.info("user_seq" + user_seq);
        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        CartDTO cDTO = new CartDTO();
        cDTO.setUser_seq(Integer.parseInt(user_seq));
        cDTO.setFood_num(Integer.parseInt(food_num));

        int res = cartService.cartCountadd(cDTO);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", res);

        return resultMap;

    }


    @PostMapping(value = "update_del")
    @ResponseBody
    public Map<String, Integer> update_del(HttpServletRequest request , HttpSession session, ModelMap model) throws Exception {
        log.info(getClass().getName() + "update_del start");

        String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
        String food_num = CmmUtil.nvl(request.getParameter("p_num"));
        log.info("user_seq" + user_seq);
        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        CartDTO cDTO = new CartDTO();
        cDTO.setUser_seq(Integer.parseInt(user_seq));
        cDTO.setFood_num(Integer.parseInt(food_num));

        int res = cartService.cartCountdel(cDTO);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", res);

        return resultMap;

    }







}
