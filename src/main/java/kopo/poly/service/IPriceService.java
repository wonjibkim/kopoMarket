package kopo.poly.service;

import kopo.poly.dto.priceDTO;

import java.util.List;


public interface IPriceService {

    void priceInSertInfo() throws Exception; // api 가져오기 DB에 저장

    List<priceDTO> getprice(); // 저장한 정보 가져오기
}
