package kopo.poly.controller;

import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("signup")
public class SignupController {




    /**
     *
     *
     * 회원가입 첫 페이지
     *
     *
     * */
    @GetMapping(value = "/SignupMain")
    public String Signup() {
        log.info(this.getClass().getName() + "SignUp start");

        log.info(this.getClass().getName() + "SignUp end");

        return "/signup/SignupMain";
    }

    /**
     *
     *
     * !!!!!!!!!!!!!!!!! 마켓 회원가입 !!!!!!!!!!!!
     *
     *
     *
     **/

    @Resource(name = "MarketInfoService")
    private IMarketInfoService marketInfoService;


    @GetMapping(value = "/SignupMarket")
    public String marketSignup() {
        log.info(this.getClass().getName() + "marketSignup start");

        log.info(this.getClass().getName() + "marketSignup end");
        return "/signup/SignupMarket";
    }

    @PostMapping(value = "insertMarketInfo")
    public String insertMarketInfo(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName() + " insertMarketInfo start!");

        String msg = "";

        MarketInfoDTO mDTO = null;

        try {
            String email_market = CmmUtil.nvl(request.getParameter("emaiil_market"));
            String pwd_market = CmmUtil.nvl(request.getParameter("pwd_market"));
            String pwd2_market = CmmUtil.nvl(request.getParameter("pwd_market"));
            String name_market = CmmUtil.nvl(request.getParameter("name market"));
            String name_boss= CmmUtil.nvl(request.getParameter("name boss"));
            String addr1_market = CmmUtil.nvl(request.getParameter("addr1_market"));
            String addr2_market = CmmUtil.nvl(request.getParameter("addr2_market"));
            String cnum_market = CmmUtil.nvl(request.getParameter("cnum_market"));

            log.info("email_market :" + email_market);
            log.info("pwd market : " + pwd_market);
            log.info("pwd2market : " + pwd2_market);
            log.info("name boss : " + name_boss);
            log.info("nameMarket : " +name_market);
            log.info("addr1 : " + addr1_market);
            log.info("addr12 : " + addr2_market);
            log.info("cnum_market  : " + cnum_market);

            mDTO = new MarketInfoDTO();

            //민감정보인 이메일은 AES128-CBC로 암호화함
            mDTO.setEmail_market(EncryptUtil.encAES128CBC(email_market));
            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            mDTO.setPwd_market(EncryptUtil.encHashSHA256(pwd_market));
            mDTO.setPwd2_market(EncryptUtil.encHashSHA256(pwd2_market));

            mDTO.setName_boss(name_boss);
            mDTO.setName_market(name_market);
            mDTO.setAddr1_market(addr1_market);
            mDTO.setAddr2_market(addr2_market);

            int res = marketInfoService.insertMarketInfo(mDTO);
            log.info("회원가입결과(res) : " +res);
            if (res == 1){
                msg = "회원가입 되었습니다.";

            }else if (res == 2){
                msg = "이미 가입된 이메일 입니다";
            }else {
                msg = "오류로 인해 회원가입이 실패했습니다";
            }
        } catch (Exception e){
            msg = "저장 실패 : " + e;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName()+".insertMarketInfo end!!");

            model.addAttribute("msg",msg);

            model.addAttribute("mDTO",mDTO);
            //변수 초기화(메모리 호율화 시키기 위해 사용)
            mDTO = null;
        }
        return "/signup/MsgToList";
    }



    /**
     *
     *
     * !!!!!!!!!!!!!! 유저 회원가입 !!!!!!!!!!!!!!!!!
     *
     *
     * **/

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    @GetMapping(value = "SignupUser")
    public String userSignup() {
        log.info(this.getClass().getName() + "userSignup start ");

        log.info(this.getClass().getName() + "userSignup end ");

        return "/signup/SignupUser";
    }

    @PostMapping(value = "insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".insertUserInfo Start ");
        //회원가입 결과에 대한 메시지를 전달할 변수
        String msg = "";
        //웹 에서 받는 정보를 저장할 변수
        UserInfoDTO uDTO = null;

        try{
            String email_user = CmmUtil.nvl(request.getParameter("email_user"));
            String pwd_user = CmmUtil.nvl(request.getParameter("pwd_user"));
            String pwd2_user = CmmUtil.nvl(request.getParameter("pwd2_user"));
            String name_user = CmmUtil.nvl(request.getParameter("name_user"));
            String gender = CmmUtil.nvl(request.getParameter("gender"));
            String age_user = CmmUtil.nvl(request.getParameter("age_user"));
            String type_veganism = CmmUtil.nvl(request.getParameter("type_veganism"));


            log.info("email_user : " + email_user);
            log.info("pwd_user" + pwd_user);
            log.info("pwd2_user " + pwd2_user);
            log.info("name_user :" + name_user);
            log.info("gender : " + gender);
            log.info("age_user : " + age_user);
            log.info("type_veganism :" + type_veganism);

            uDTO = new UserInfoDTO();

            //민감정보인 이메일은 AES_128CBC로 암호화
            uDTO.setEmail_user(EncryptUtil.encAES128CBC(email_user));
            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화
            uDTO.setPwd_user(EncryptUtil.encHashSHA256(pwd_user));
            uDTO.setPwd2_user(EncryptUtil.encHashSHA256(pwd2_user));

            uDTO.setName_user(name_user);
            uDTO.setGender(gender);
            uDTO.setAge_user(age_user);
            uDTO.setType_veganism(type_veganism);

            int res = userInfoService.insertUserInfo(uDTO);
            log.info("회원가입 결과(res) : " + res);

            if (res == 1){
                msg = "회원가입 되었습니다.";

            }else if (res == 2){
                msg = "이미 가입된 이메일 주소입니다.";
            }else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";
            }
        }catch (Exception e ){
            msg=  "실패하였습니다. " + e;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info("msg" + msg);
            log.info(this.getClass().getName()+ ".insertUserInfo end!!");

            model.addAttribute("msg ", msg);
            model.addAttribute("uDTO",uDTO);

            uDTO = null;
        }
        return "/signup/SignupSuccess";
    }
}
