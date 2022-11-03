package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

    private String user_seq; //시퀀스 번호
    private String email_user; //유저 이메일
    private String pwd_user; //유저 비밀번호
    private String pwd2_user; //유저 비밀번호확인
    private String name_user; //유저이름
    private String gender; //유저 성별
    private String age_user; //유저 나이
    private String type_veganism; //채식타입

    // 회원가입시, 중복가입을 방지 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함
    // DB테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    private String exists_yn;

}
