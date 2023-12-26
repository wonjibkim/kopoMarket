package kopo.poly.service;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserInfoDTO;

import java.util.Map;

public interface IUserInfoService {


    String mailCodeCheck(String email) throws Exception;


    // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    // 회원가입하기 (회원정보 등록하기)
    int insertUserInfo(UserInfoDTO uDTO) throws Exception;

    // 비밀찾기 전 아이디 체크 확인
    Map <String, String> userPCodeCheck(UserInfoDTO uDTO) throws Exception;

    void newPwdUser(UserInfoDTO uDTO) throws Exception;

}
