package kopo.poly.controller;

import kopo.poly.dto.CartDTO;
import kopo.poly.service.ICartService;
import kopo.poly.service.IMapService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "Cart")
public class CartController {

    @Resource(name = "CartService")
    private ICartService cartService;


    @PostMapping(value = "cart_add") // 상품 카트 추가
    public String cart_add(HttpServletRequest request, HttpSession session, ModelMap model ) throws Exception{
        log.info(getClass().getName() + "cart_add start");

        String url = ""; // 상황에 따라 url 이동
        String msg= ""; // 상황에 따라 alert창 띄우기
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

            if (res == 1){
                msg ="카트 담기에 성공하였습니다";
                url = "";


            }else if (res == 2){
                msg = "이미 담겨져 있는 품목입니다";
                url = "";
            }else {
                msg="오류로 인해서 카트에 담기에 실패하였습니다";
            }

        }catch (Exception e){ // 실패시 나오는 에러메세지
            msg=  "실패하였습니다. " + e;
            url= "/signup/SingupUser";
            log.info(e.toString());
            e.printStackTrace();

        }finally {
            log.info(getClass().getName() + "cart_add end");

            model.addAttribute("msg",msg);
            model.addAttribute("url",url);

            cDTO = null;


        }


        return "/redirect";
    }

}
