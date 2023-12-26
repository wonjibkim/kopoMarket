package kopo.poly.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {

    private String p_num; //pk
    private String p_name; //이름
    private String p_price; // 가격
    private String p_sell; //수량
    private String p_info; // 상품설명
    private String p_period; //유통기한
    private String p_category; // 카테고리
    private String p_enroll; //등록일

    private String p_filePath; //파일경로
    private String p_fileName; //파일이름

    private String p_discount; //할인율
    private String p_ancestry; //원산지
    private String p_weight; //무게

    private String p_barcode; //바코드
    private String market_seq;




}
