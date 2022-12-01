package kopo.poly.controller;



import kopo.poly.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
;

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

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");
        kakaopay.kakaoPayReady();

        return "redirect:" + kakaopay.kakaoPayReady();

    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, ModelMap model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));


        return "";
    }







}
