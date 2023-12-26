package kopo.poly.persistance.mapper;


import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMarketInfoMapper {


    // 유저 회원가입
    int insertMarketInfo(MarketInfoDTO mDTO) throws Exception;


    // 회원가입 전 중복체크 )(DB조회)
    MarketInfoDTO getMarketExists(MarketInfoDTO mDTO) throws Exception;


    //로그인을 위해 아이디와 비밀번호 일치하는지 확인
    MarketInfoDTO getMarketLoginCheck(MarketInfoDTO mDTO) throws Exception;

}
