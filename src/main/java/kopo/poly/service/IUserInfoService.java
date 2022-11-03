package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

public interface IUserInfoService {

    // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
    int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    int insertUserInfo(UserInfoDTO uDTO) throws Exception;
}
