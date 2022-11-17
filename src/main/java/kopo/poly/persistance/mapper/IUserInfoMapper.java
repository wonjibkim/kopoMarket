package kopo.poly.persistance.mapper;

import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserInfoMapper {


    // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    // 유저 회원가입
    int insertUserInfo(UserInfoDTO uDTO) throws Exception;

    //회원가입전 중복체크 ( DB 조회하기 )
    UserInfoDTO getUserExists(UserInfoDTO uDTO) throws Exception;
}

