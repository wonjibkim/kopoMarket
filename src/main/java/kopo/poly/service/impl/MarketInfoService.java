package kopo.poly.service.impl;


import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.persistance.mapper.IMarketInfoMapper;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("MarketInfoService")
@Slf4j
@RequiredArgsConstructor
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

        @Override
        public int getMarketLoginCheck(MarketInfoDTO mDTO) throws Exception {
                return 0;
        }

}

