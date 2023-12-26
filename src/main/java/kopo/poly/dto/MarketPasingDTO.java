package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketPasingDTO {
    private String bizplc_nm;
    private String refine_wgs84_lat;//위도
    private String refine_wgs84_logt;//경도
    private String licensg_de;//인허가 날짜
    private String refine_roadnm_addr; //주소

}
