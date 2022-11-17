package kopo.poly.dto;

import lombok.Data;

@Data
public class CartDTO {

    private int cart_num; //카트번호
    private int cart_count; // 카트에 담은 갯수
    private String cart_date; //카트에 담은 날짜
    private int user_seq; // userinfo테이블에 있는 회원정보 pk
    private int food_num; // food 테이블에 있는 음식 pk




    private String exists_yn;

}
