package kopo.poly.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketInfoDTO {

    private String market_seq; // 시컨스번호
    private String email_market; // 마켓이메일
    private String pwd_market;  //마켓비밀번호
    private String pwd2_market; //마켓비밀번호 확인
    private String name_market; //마트이름
    private String addr1_market;//마켓주소1
    private String addr2_market;//마켓주소2
    private String cnum_market; //마켓대표번

    /*회원가입시, 중복가입을 방지위해 사용할 변수
     * DB를 조회해서 회원이 존재하면 Y값을 반환함
     * DB테이블에 존재하지 않는 가상의 칼럼 ALIAS */
    private String exists_yn;

    private String b_no; //사업자등록번호
    private String start_dt; // 개업날자
    private String p_nm; //대표 이름
}
