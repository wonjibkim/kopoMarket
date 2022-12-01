package kopo.poly.dto;

import lombok.Data;

@Data
public class PayDTO {

    private String p_num; //pk
    private String p_name; //이름
    private String p_price; // 가격
    private String p_sell; //수량
    private String market_seq;


    private int cart_num; //카트번호
    private int cart_count; // 카트에 담은 갯수
    private int user_seq; // userinfo테이블에 있는 회원정보 pk
    private int food_num; // food 테이블에 있는 음식 pk
    private String purchase;





    private String exists_yn;

}
