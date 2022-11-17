package kopo.poly.service.impl;


import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.persistance.mapper.IMarketInfoMapper;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("MarketInfoService")
public class MarketInfoService implements IMarketInfoService {

        private final IMarketInfoMapper marketInfoMapper;


        @Override
        public int insertMarketInfo(MarketInfoDTO mDTO) throws Exception {
                //회원가입 성공: 1,
                //아이디 중복 : 2,
                //기타에러발생 : 0,
                int res = 0;

                //controller에서 값이 정상적으로 못넘어오는 경우를 대비하기 위함
                if (mDTO == null){
                        mDTO = new MarketInfoDTO();
                }
                //회원가입 중복방지위해 db에서 데이터 조회
                MarketInfoDTO rDTO = marketInfoMapper.getMarketExists(mDTO);

                //mapper에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용
                if (rDTO == null ){
                        rDTO = new MarketInfoDTO();
                }
                //중복된 회원정보가 있는경우, 결과값을 2로 변경하고, 더 이상 작업을 진행하지 않음
                if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
                        res = 2;
                        //회원정보가 중복이 아니므로 회원가입 진행
                }else {
                        int success = marketInfoMapper.insertMarketInfo(mDTO);
                        //db에 데이터가 등록되었다면 회원가입 성공//
                        if(success > 0 ){
                                res = 1;
                        }else {
                                res = 0;
                        }
                }
                return res;
        }


        /** 마켓 로그인을 위한 아이디 비밀번호 확인 */
        @Override
        public int getMarketLoginCheck(MarketInfoDTO mDTO) throws Exception {

                //로그인 성공:1
                //실패 :0
                int res = 0;

                //로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper호출
                MarketInfoDTO rDTO = marketInfoMapper.getMarketLoginCheck(mDTO);

                if (rDTO == null){
                        rDTO = new MarketInfoDTO();
                }

                /*
                * ****************
                * 로그인 성공 여부 체크 시작 !!
                * @@@@@@@@@@@@@@@@@
                *
                * userInfoMapper 로 부터 Select 쿼리의 결과로 회원아이디를 받아왔다면 로그인 성공!!
                *
                * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다,
                * 따라서 .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
                * 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
                * */
                if (CmmUtil.nvl(rDTO.getEmail_market()).length() > 0) {
                        res = 1;
                }
                /* ##############
                *  로그인 성공 여부 체크 끝
                *  #############*/
                return res;
        }

}

