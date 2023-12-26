package kopo.poly.controller;


import kopo.poly.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

@Controller
@Slf4j
@SessionAttributes({"tid","order"})
public class PayController {

    @Resource(name = "PayService")
    private IPayService kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }


    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, ModelMap model) {
        log.info("kakaoPaySuccess start............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));

        return "/market/kaypaysubmit";

    }







}
