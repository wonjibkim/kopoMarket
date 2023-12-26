package kopo.poly.controller;

/*import jdk.internal.invoke.VMStorageProxy;*/
import kopo.poly.dto.MailDTO;
import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMailService;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.service.IUserInfoService;

import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import kopo.poly.util.MailCodeUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("signup")
public class SignupController {



    /** 사업자등록번호 api 연습 페이지 */
    @GetMapping(value = "/psign")
    public String psign(){
        return "/signup/SignupPPP";
    }
    @GetMapping("/marketBnumCheck")
    public String ppsign(HttpSession session, HttpServletRequest request){


        return "/signup/BnumCheck";
    }


    /**@@@@@@@@@@@@@@@@@@@@@2
     * 회원가입 첫 페이지
     * @@@@@@@@@@@@@@@@@@
     *
     *
     *
     * */
    @GetMapping(value = "/SignupMain")
    public String Signup() {
        log.info(this.getClass().getName() + "SignUp start");

        log.info(this.getClass().getName() + "SignUp end");

        return "/signup/SignupMain";
    }



    /** @@@@@@@@@@@@@@@@@@@@@
     *  유저 메일 인증 받기
     * @@@@@@@@@@@@@@@@@@@@@
     *
     *
     * */
    //메일 서비스 연결하기
    @Resource(name = "MailService")
    private IMailService mailService;

    //처음 메일 보낼 화면
    @GetMapping(value = "/UserMailCheck")
    public String mailAuth(){
        log.info(this.getClass().getName() + " 유저 메일 체크 스타트 !! ");

        log.info(this.getClass().getName() + " 유저 메일 체크 끝 !! ");

        return "/mail/UserMailCheck";
    }

    // 메일 발송하기
    @PostMapping(value = "/UserMailCheck/MailCodeCheck")
    public String mailAuth2(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+"mail.SendMail start!!");

        //웹 URL로부터 전달받는 값들 (request.getParameter --> jsp에서 Controller
        String email_user = CmmUtil.nvl(request.getParameter("email_user"));
        log.info("email_user : " + email_user);// 받는사람


        String emailCode = MailCodeUtil.createKey();


        //메일 발송할 정보 넣기 위한 DTO객체 생성하기
        MailDTO mDTO = new MailDTO();
        //웹에서 받은 값을 DTO에 넣기
        mDTO.setToEmail(email_user); //받는사람을 DTO에 저장
        mDTO.setMail_code(emailCode); //인증번호를 dto에 저장



        int code = mailService.insertMailCode(mDTO);


        //메일 발송하기
        int res = mailService.doSendMail(mDTO);

        if (res == 1) {
            log.info(this.getClass().getName()+"mail.SendMail success!@!");
        }else {
            log.info(this.getClass().getName()+"mail.SendMail fail!!");
        }

        //메일 발송 결과를 JSP에 전달하기 (데이터 전달시, 숫자보단 문자열이 컨트ㅗㄹ하기 편리하기 때문에 강제로 숫자를 문자로 변환함)
        model.addAttribute("res", String.valueOf(res));
        model.addAttribute("email_user", email_user);

        log.info(this.getClass().getName()+"mailSendMail End!!");

        return "/mail/UserMailCodeCheck";
    }

    //@PostMapping("")










    /** !!!!!!!!!!!!!!!!!!!!!!!!!
     *  마켓 회원가입
     *  !!!!!!!!!!!!!!!!!!
     *
     *
     *
     *
     *
     **/

    @Resource(name = "MarketInfoService")
    private IMarketInfoService marketInfoService;


    @PostMapping(value = "/SignupMarket")
    public String marketSignup(HttpServletRequest request, HttpSession session, ModelMap model) {
        log.info(this.getClass().getName() + "marketSignup start");

        //사업자등록번호를 저장해서 넘기기
        String b_no = CmmUtil.nvl(request.getParameter("b_no"));
        String start_dt = CmmUtil.nvl(request.getParameter("start_dt"));
        String p_nm = CmmUtil.nvl(request.getParameter("p_nm"));
        log.info("b_no : " + b_no);
        log.info("start_dt : " + start_dt);
        log.info("p_nm : " + p_nm);

        session.setAttribute("b_no", b_no);
        session.setAttribute("start_dt",start_dt);
        session.setAttribute("p_nm",p_nm);

        model.addAttribute("b_no",b_no);
        model.addAttribute("start_dt",start_dt);
        model.addAttribute("p_nm",p_nm);



        log.info(this.getClass().getName() + "marketSignup end");
        return "/signup/SignupMarket";
    }

    @ResponseBody /* 이 어노테이션이 붙은 함수의 결과값은 무조건 json으로 반환한다.*/
    @PostMapping(value = "/signup/SignupMarket/email_send")
    public Map<String,Object> marketEmail(HttpServletRequest request, HttpSession session){
        log.info(this.getClass().getName()+"marketEmail_send start!");

        Map<String, Object> rMap = new HashMap<>();

        String email_market = CmmUtil.nvl(request.getParameter("email_market"));

        // int emailCheck = 중복확인함수(email_user)
        // if emailCheck가 1(중복아닐때)와 0(중복일때) 경우 나눠서
        //1일때 mailSend(MailDTO) 써야하는데 이때 인증코드(Random_Pin)와 보낼 메일을 set해서 써야합니
        // 전송 성공실패 여부 result = 1 or 0 으로 판단



        log.info(this.getClass().getName()+"marketEmail_send end!");

        return rMap;
    }

    @PostMapping(value = "insertMarketInfo")
    public String insertMarketInfo(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception{
        log.info(this.getClass().getName() + " insertMarketInfo start!");

        String msg = "";
        String url = "";

        MarketInfoDTO mDTO = null;

        try {
            String email_market = CmmUtil.nvl(request.getParameter("email_market"));
            String pwd_market = CmmUtil.nvl(request.getParameter("pwd_market"));
            String pwd2_market = CmmUtil.nvl(request.getParameter("pwd2_market"));
            String name_market = CmmUtil.nvl(request.getParameter("name_market"));
            String addr1_market = CmmUtil.nvl(request.getParameter("addr1_market"));
            String addr2_market = CmmUtil.nvl(request.getParameter("addr2_market"));
            String cnum_market = CmmUtil.nvl(request.getParameter("cnum_market"));


            String b_no = CmmUtil.nvl((String) session.getAttribute("b_no"));
            String start_dt = CmmUtil.nvl((String) session.getAttribute("start_dt"));
            String p_nm = CmmUtil.nvl((String) session.getAttribute("p_nm"));

            log.info("email_market :" + email_market);
            log.info("pwd market : " + pwd_market);
            log.info("pwd2market : " + pwd2_market);
            log.info("nameMarket : " +name_market);
            log.info("addr1 : " + addr1_market);
            log.info("addr12 : " + addr2_market);
            log.info("cnum_market(전화번호)  : " + cnum_market);

            log.info("business number : " + b_no);
            log.info("start_dt : (시작날짜) : " + start_dt);
            log.info("p_nm ( 대표자 성함) : " + p_nm);

            mDTO = new MarketInfoDTO();

            //민감정보인 이메일은 AES128-CBC로 암호화함
            //이메일을 찾아야하기때문에 암호화하지 않음
            mDTO.setEmail_market(email_market);
            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            mDTO.setPwd_market(EncryptUtil.encHashSHA256(pwd_market));
            // 비밀번호 확인은 저장할 필요가 없음
            // mDTO.setPwd2_market(EncryptUtil.encHashSHA256(pwd2_market));


            mDTO.setName_market(name_market);
            mDTO.setAddr1_market(addr1_market);
            mDTO.setAddr2_market(addr2_market);
            mDTO.setCnum_market(cnum_market);

            mDTO.setB_no(b_no);
            mDTO.setStart_dt(start_dt);
            mDTO.setP_nm(p_nm);

            int res = marketInfoService.insertMarketInfo(mDTO);
            log.info("회원가입결과(res) : " +res);
            if (res == 1){
                msg = "회원가입 되었습니다.";
                url = "/login/loginMarket";

            }else if (res == 2){
                msg = "이미 가입된 이메일 입니다";
                url = "/signup/SignupMarket";
            }else {
                msg = "오류로 인해 회원가입이 실패했습니다";
                url = "/signup/SignupMarket";
            }
        } catch (Exception e){
            msg = "저장 실패 : " + e;
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName()+".insertMarketInfo end!!");

            model.addAttribute("msg",msg);
            model.addAttribute("url",url);

            model.addAttribute("mDTO",mDTO);
            //변수 초기화(메모리 호율화 시키기 위해 사용)
            mDTO = null;
        }
        return "/redirect";
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

    @PostMapping(value = "userMailCodeCheck")
    public String userMailCodeCheck(HttpServletRequest request, HttpSession session,ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "userSignup CodeCheck start ");

        // 로그인 처리 결과를 저장할 변수 (로그인 성공 = 1, 아이디,비밀번호 불일치 실패: 0 에러:2
        //int res = 0;


        String msg = null;
        String url = "";

        //웹화면에서 받는 정보를 저장할 변수 ( 인증코드 )

        String email_user = request.getParameter("email_user");
        log.info("email_user : "+email_user);
        String mail_code1 = request.getParameter("mail_code");
        /*여기서 인증번호랑 아이디를 비교를 해라 */
        String mail_code2 = userInfoService.mailCodeCheck(email_user);
        log.info("mailcode2 : " + mail_code2);
        log.info("mailcode1 : " + mail_code1);

        if ( mail_code1.equals(mail_code2)){
            url = "/signup/SignupUser?email_user="+email_user; //K, V 로 보냄
            msg = "인증되었습니다. 회원가입 가능합니다";
        }else {

            url = "/signup/UserMailCheck/MailCodeCheck";
            msg ="코드가 틀려요. 다시 인증해주세요 ";
        }



        model.addAttribute("url", url);
        model.addAttribute("msg", msg);
        model.addAttribute("email_user", email_user); // 유저회원가입 보내야 다음페이지 고정


        log.info(this.getClass().getName() + "userSignup CodeCheck end ");

        return "/redirect";
    }
    @GetMapping(value = "SignupUser")
    public String SignupUser(HttpServletRequest request, ModelMap model){
        String email_user = CmmUtil.nvl((String) request.getParameter("email_user"));

        log.info("email_user : "+email_user);

        model.addAttribute("email_user", email_user);

        return "/signup/SignupUser";
    }

    @PostMapping(value = "insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start ");
        //회원가입 결과에 대한 메시지를 전달할 변수
        String msg = "";
        String url = "";
        //웹 에서 받는 정보를 저장할 변수
        UserInfoDTO uDTO = null;

        try {
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
            //메일발송이 아닌 저장으로 찾아야하기 때문에 암호화 하지 않아도 됨
            uDTO.setEmail_user(email_user);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화
            uDTO.setPwd_user(EncryptUtil.encHashSHA256(pwd_user));

            // pwd2 는 db에 저장할 필요는 없음
            // uDTO.setPwd2_user(EncryptUtil.encHashSHA256(pwd2_user));

            uDTO.setName_user(name_user);
            uDTO.setGender(gender);
            uDTO.setAge_user(age_user);
            uDTO.setType_veganism(type_veganism);

            int res = userInfoService.insertUserInfo(uDTO);
            log.info("회원가입 결과(res) : " + res);

            if (res == 1) {
                msg = "회원가입 되었습니다. 환영합니다";
                url = "/login/loginUser";

            } else if (res == 2) {
                msg = "이미 가입된 이메일 주소입니다.";
                url = "/signup/SignupUser";
            } else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";
                url = "/signup/SignupUser";
            }
        } catch (Exception e) {
            msg = "실패하였습니다. " + e;
            url = "/signup/SingupUser";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info("msg" + msg);
            log.info(this.getClass().getName() + ".insertUserInfo end!!");

            //회원가입 여부 결과 메시지 전달
            model.addAttribute("msg", msg);
            //url 주소로ㅓ 이동
            model.addAttribute("url", url);

            //회원가입 여부 결과 메시지 전달
            model.addAttribute("uDTO", uDTO);


            // 변수 초기화 (메모리 효율화 시키기 위해 사용)
            uDTO = null;
        }
        return "/redirect";
    }
}
