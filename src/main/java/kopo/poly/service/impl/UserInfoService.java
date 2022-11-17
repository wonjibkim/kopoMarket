package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserInfoMapper;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

    // RequiredArgsConstructor 어노테이션으로 생성자를 자동 생성함
    // userInfoMapper 변수에 이미 메모리에 올라간 Mapper 객체를 넣어줌
    // 예전에는 autowired 어노테이션를 통해 설정했었지만, 이젠 생성자를 통해 객체 주입함
    private final IUserInfoMapper userInfoMapper;


    /**
     * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
     *
     * @param pDTO 로그인을 위한 회원아이디, 비밀번호
     * @return 로그인된 회원아이디 정보
     */
    @Override
    public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        // 로그인 성공 : 1, 실패 : 0
        int res = 0;

        // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);


        log.info(pDTO.getEmail_user());
        log.info(pDTO.getPwd_user());
        log.info(String.valueOf(userInfoMapper.getUserLoginCheck(pDTO)));
        log.info(String.valueOf(rDTO));

        if (rDTO == null) {
            rDTO = new UserInfoDTO();

        }

        /*
         * #######################################################
         *        				로그인 성공 여부 체크 시작!!
         * #######################################################
         */

        /*
         * userInfoMapper로 부터 SELECT 쿼리의 결과로 회원아이디를 받아왔다면, 로그인 성공!!
         *
         * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다.
         * 따라서  .length() 함수를 통해 회원아이디의 글자수를 가져와 0보다 큰지 비교한다.
         * 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
         */
        if (CmmUtil.nvl(rDTO.getEmail_user()).length() > 0) {
            res = 1;
        }

        /*
         * #######################################################
         *        				로그인 성공 여부 체크 끝!!
         * #######################################################
         */

        return res;
    }


    /**
     *
     * 회원가입 !!!!!
     *
     *
     * */
    @Override
    public int insertUserInfo(UserInfoDTO uDTO) throws Exception {
        //회원가입 성공 : 1,
        //아이디중복으로 인한 가입 취소 : 2
        //기타 에러발생 :0
        int res = 0;

        //controller 에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용
        if (uDTO == null){
            uDTO = new UserInfoDTO();
        }
        //회원가입 중복 방지를 위해 DB에서 데이터 조회
        UserInfoDTO rDTO = userInfoMapper.getUserExists(uDTO);
        log.info("uDTO : " + uDTO);

        //mapper에서 값이 정상적으로 못넘어오는 경우를 대비하기 위해
        if (rDTO == null){
            rDTO = new UserInfoDTO();
        }

        //중복된 회원정보가 있는경우, 결과값을 2로 변경하고, 더 이상 작업을 진행하지 않음
        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            res = 2;

            //회원가입이 중복이 아니므로 회원가입 진행함
        }else {
            log.info(uDTO.getName_user());
            int success = userInfoMapper.insertUserInfo(uDTO);
            //db에 데이터가 등록되없다면
            if(success > 0){
                res = 1;
            }else {
                res = 0;
            }
        }
        return res;
    }
}
