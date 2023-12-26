package kopo.poly.service.impl;


import kopo.poly.dto.KakaoPayApprovalVO;
import kopo.poly.dto.kPayDTO;
import kopo.poly.persistance.mapper.IPayMapper;
import kopo.poly.service.IPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@Service("PayService")
@Log
public class PayService implements IPayService {



    private final IPayMapper payMapper;

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(kPayDTO gDTO) {

        RestTemplate restTemplate = new RestTemplate();




        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "178f8dd41583e553c0d315f919e4e8b2");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");



        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");

        params.add("partner_order_id", gDTO.getPay_seq()); //cart pk
        params.add("partner_user_id", gDTO.getEmail_user()); // user_id
        params.add("item_name", gDTO.getP_name()); // 품명제일 대표 하나
        params.add("quantity", gDTO.getCart_count()); // 수량
        params.add("total_amount",gDTO.getPrice_sum());//총합

        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:11000/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:11000/kakaoPayCancel");
        params.add("fail_url", "http://localhost:11000/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";

    }



    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        kPayDTO kPTO = payMapper.getPayKaKao();
        String sum_price =payMapper.getSumprice();
        kPTO.setPrice_sum(sum_price);

        log.info(kPTO.getPay_seq());
        log.info(kPTO.getEmail_user());
        log.info(kPTO.getPrice_sum());


        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "178f8dd41583e553c0d315f919e4e8b2");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());

        params.add("partner_order_id", kPTO.getPay_seq());
        params.add("partner_user_id", kPTO.getEmail_user());
        params.add("pg_token", pg_token);
        params.add("total_amount", kPTO.getPrice_sum());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            payMapper.PayEnd();
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }








}
