package kopo.poly.service;


import kopo.poly.dto.kPayDTO;

public interface IPayService {

    String kakaoPayReady(kPayDTO gDTO);

    Object kakaoPayInfo(String pg_token);
}
