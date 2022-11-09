package kopo.poly.controller;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMailService;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * 메일 인증 받기
 * @@@@@@@@@@@@@@@@@@@@@
 *
 *
 * */
    //메일 서비스 연결하기
    @Resource(name = "MailService")
    private IMailService mailService;


    //처음 메일 보낼 화면
    @GetMapping(value = "/mailAuth")
    public String mailAuth(){

        return "/mail/mailAuth";
    }
    // 메일 발송하기
    @PostMapping(value = "mailauth")
    public String mailauth(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+"mail.SendMail start!!");

        //웹 URL로부터 전달받는 값들
        String email_user = CmmUtil.nvl(request.getParameter("email_user")); // 받는사람
        //String title = CmmUtil.nvl(request.getParameter("title")); // 제목
        //String contents = CmmUtil.nvl(request.getParameter("contents")); //내용

        //메일 발송할 정보 넣기 위한 DTO객체 생성하기
        MailDTO mDTO = new MailDTO();
        //웹에서 받은 값을 DTO에 넣기
        mDTO.setEmail_user(email_user); //받는사람을 DTO에 저장
        //mDTO.setTitle(title); // 제목을 DTO에 저장
        //mDTO.setContents(contents); // 내용을 DT0에 저장

        //메일 발송하기
        int res = mailService.doSendMail(mDTO);

        if (res == 1) {
            log.info(this.getClass().getName()+"mail.SendMail success!@!");
        }else {
            log.info(this.getClass().getName()+"mail.SendMail fail!!");
        }

        //메일 발송 결과를 JSP에 전달하기 (데이터 전달시, 숫자보단 문자열이 컨트ㅗㄹ하기 편리하기 때문에 강제로 숫자를 문자로 변환함)
        model.addAttribute("res", String.valueOf(res));

        log.info(this.getClass().getName()+"mailSendMail End!!");

        return "/mail/SendMailCode";
    }







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
        String url = "";

        MarketInfoDTO mDTO = null;

        try {
            String email_market = CmmUtil.nvl(request.getParameter("email_market"));
            String pwd_market = CmmUtil.nvl(request.getParameter("pwd_market"));
            String pwd2_market = CmmUtil.nvl(request.getParameter("pwd2_market"));
            String name_market = CmmUtil.nvl(request.getParameter("name_market"));
            String name_boss= CmmUtil.nvl(request.getParameter("name_boss"));
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
            //이메일을 찾아야하기때문에 암호화하지 않음
            mDTO.setEmail_market(email_market);
            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            mDTO.setPwd_market(EncryptUtil.encHashSHA256(pwd_market));
            // 비밀번호 확인은 저장할 필요가 없음
            // mDTO.setPwd2_market(EncryptUtil.encHashSHA256(pwd2_market));

            mDTO.setName_boss(name_boss);
            mDTO.setName_market(name_market);
            mDTO.setAddr1_market(addr1_market);
            mDTO.setAddr2_market(addr2_market);
            mDTO.setCnum_market(cnum_market);

            int res = marketInfoService.insertMarketInfo(mDTO);
            log.info("회원가입결과(res) : " +res);
            if (res == 1){
                msg = "회원가입 되었습니다.";
                url = "/login/login";

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
        String url = "";
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

            if (res == 1){
                msg = "회원가입 되었습니다. 환영합니다";
                url = "/login/login";

            }else if (res == 2){
                msg = "이미 가입된 이메일 주소입니다.";
                url = "/signup/SignupUser";
            }else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";
                url = "/signup/SignupUser";
            }
        }catch (Exception e ){
            msg=  "실패하였습니다. " + e;
            url= "/signup/SingupUser";
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info("msg" + msg);
            log.info(this.getClass().getName()+ ".insertUserInfo end!!");

            //회원가입 여부 결과 메시지 전달
            model.addAttribute("msg", msg);
            //url 주소로ㅓ 이동
            model.addAttribute("url", url);


            //회원가입 여부 결과 메시지 전달
            model.addAttribute("uDTO",uDTO);



            // 변수 초기화 (메모리 효율화 시키기 위해 사용)
            uDTO = null;
        }
        return "/redirect";
    }
}
