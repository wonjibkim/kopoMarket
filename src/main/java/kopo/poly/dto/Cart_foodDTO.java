package kopo.poly.dto;

import lombok.Data;

@Data
public class Cart_foodDTO {


    private int cart_count; // 카트에 담은 갯수
    private String cart_date; //카트에 담은 날짜
    private int user_seq; // userinfo 테이블에 있는 회원정보 pk
    private int food_num; // food 테이블에 있는 음식 pk


    private String p_barcode; //바코드




    private String p_price; // 가격
    private String p_ancestry; //원산지
    private String p_name; //이름
    private int cart_num;//카트 테이블 pk
    private String p_sell;
    private String p_filePath; //파일경로
    private String p_fileName; //파일이름
    private String p_num; //pk

}
