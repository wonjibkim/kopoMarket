package kopo.poly.service;

import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;

import java.util.List;

public interface IMapService {

    List<MarketInfoDTO> MartMap(); // 등록된 마트위치 마커표시

    List<MarketPasingDTO>pasingMap(); // 경기도 유통판매업 마커 표시

    void maketInsertInfo() throws Exception; // api 가져오기 service에서만 호츌
}
