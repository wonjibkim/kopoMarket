package kopo.poly.service;


import kopo.poly.dto.MarketInfoDTO;

public interface IMarketInfoService {

    int insertMarketInfo(MarketInfoDTO mDTO) throws Exception;


    MarketInfoDTO getMarketLoginCheck(MarketInfoDTO mDTO) throws Exception;
}
