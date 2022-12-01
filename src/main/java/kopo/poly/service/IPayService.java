package kopo.poly.service;



public interface IPayService {

    String kakaoPayReady();

    Object kakaoPayInfo(String pg_token);
}
