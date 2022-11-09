package kopo.poly.controller;

import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMarketInfoService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.service.impl.MarketInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "login")
public class LoginController {

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;


    @Resource(name = "MarketInfoService")
    private IMarketInfoService marketInfoService;

    @GetMapping(value = "MyPage")
    public String MyPage(HttpSession session) {
        log.info(this.getClass().getName() + "MyPage start ");
        log.info((String) session.getAttribute("SS_USER_ID"));
        log.info(this.getClass().getName() + "MyPage end ");

        return "/signup/MyPage";
    }

    @GetMapping(value = "logout")
    public String logout() {
        log.info(this.getClass().getName() + "logout start ");

        log.info(this.getClass().getName() + "logout end ");

        return "/signup/logout";
    }


    /** ###########################
     *          마켓 로그인
     * ###########################*/

    @GetMapping(value = "loginMarket")
    public String loginMarket(){
        log.info(this.getClass().getName()+" loginmarket letssssgo!");

        return "/signup/loginMarket";
    }

    @PostMapping(value = "getMarketLoginCheck")
    public String getMarketLoginCheck(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName() + "getUserLoginCheck start!!");
        //로그인 처리 결과를 저장할 변수 (로그인 성공:1    아이디, 비밀번호 불일치 실패 : 0     시스템 에러 :2
        int res = 0;
        String msg = null;
        String url = null;

        //웹 회원정보 입력화면 _ 에서 받는 정보를 저장할 변수 서언
        MarketInfoDTO mDTO = null;

        try {
            /* !!!!!!!!!!!!!!!!!!!!!
            * 웹 에서 받는 정보를 String 번수에 저장 시작 !!
            * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 Stribng 변수에 저장함
            * !!!!!!!!!!!!!!!!!!!!*/
            String email_market = CmmUtil.nvl(request.getParameter("email_market"));
            String pwd_market = CmmUtil.nvl(request.getParameter("pwd_market"));

            /* 값을 받았으면 반드시 로그를 찍어 값이 제대로 들어ㅓ오는지 파악해야함*/
            log.info("email_market"+ email_market);
            log.info("pwd_market" + pwd_market);

            //웹 화면에서 받는 정보를 저장할 변수를 메모리에 올리기
            mDTO = new MarketInfoDTO();
            mDTO.setEmail_market(email_market);
            // 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화
            mDTO.setPwd_market(EncryptUtil.encHashSHA256(pwd_market));

            //로그인을 위해 아이디와 비밀번호가 일치히는지 확인하기 위한 userinfoService호출하기
            res = marketInfoService.getMarketLoginCheck(mDTO);

            log.info("res : " + res);

            /* !!!!!!!!!!!!!!!!!!
            * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
            *
            * 세션은 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다 */
            if (res == 1){
                session.setAttribute("SS_EMAIL_MARKET", email_market);
                log.info((String) session.getAttribute("SS_EMAIL_MARKET"));
                msg="로그인 성공";
                url="/market/index";
            }else {
                msg = "로그인 실패";
                url="/login/loginMarket";
            }

        } catch (Exception e){
            //저장이 실패되면 사용자에게 보여줄 메시지
            res = 2;
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName()+"insertMarketInfo end !");
            /* 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
            * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이 있어,
            * 문자 유형(String )으로 강제 형변환하여 jsp에 전달한다 */
            model.addAttribute("msg",msg);
            model.addAttribute("url",url);
            model.addAttribute("res" , String.valueOf(res));


            mDTO = null;
        }

        return "/redirect";
    }











    /** ##########################3
     *          유저 로그인
     * ##########################
     *
     *
     *
     * 유저 로그인을 위한 입력 화면으로 이동
     */
    @GetMapping(value = "loginUser")
    public String loginForm() {
        log.info(this.getClass().getName() + ".user/loginForm ok!");

        return "/signup/loginUser";
    }

    /**
     * 유저 로그인 처리 및 결과 알려주는 화면으로 이동
     */
    @PostMapping(value = "/getUserLoginCheck")
    public String getUserLoginCheck(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".getUserLoginCheck start!");

        //로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로인한 실패 : 0, 시스템 에러 : 2)
        int res = 0;
        String msg = null;
        String url="";

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try {

            /*
             * ########################################################################
             *        웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
             *
             *    무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
             * ########################################################################
             */

            String email_user = CmmUtil.nvl(request.getParameter("email_user")); //아이디
            String pwd_user = CmmUtil.nvl(request.getParameter("pwd_user")); //비밀번호

            /*
             * ########################################################################
             *        웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 끝!!
             *
             *    무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
             * ########################################################################
             */

            /*
             * ########################################################################
             * 	 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
             * 						반드시 작성할 것
             * ########################################################################
             * */
            log.info("email_user : " + email_user );
            log.info("pwd_user : " + pwd_user);

            /*
             * ########################################################################
             *        웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
             *
             *        무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
             * ########################################################################
             */


            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setEmail_user(email_user);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setPwd_user(EncryptUtil.encHashSHA256(pwd_user));

            /*
             * ########################################################################
             *        웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 끝!!
             *
             *        무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
             * ########################################################################
             */

            // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
            res = userInfoService.getUserLoginCheck(pDTO);

            log.info("res : " + res);
            /*
             * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
             *
             * 세션은 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다.
             * 			 *
             * 예) 톰켓에 100명의 사용자가 로그인했다면, 사용자 각각 회원아이디를 메모리에 저장하며.
             *    메모리에 저장된 객체의 수는 100개이다.
             *    따라서 과도한 세션은 톰켓의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기때문에,
             *    최소한으로 사용하는 것을 권장한다.
             *
             * 스프링에서 세션을 사용하기 위해서는 함수명의 파라미터에 HttpSession session 존재해야 한다.
             * 세션은 톰켓의 메모리에 저장되기 때문에 url마다 전달하는게 필요하지 않고,
             * 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸수 있다.
             * */
            if (res == 1) { //로그인 성공

                /*
                 * 세션에 회원아이디 저장하기, 추후 로그인여부를 체크하기 위해 세션에 값이 존재하는지 체크한다.
                 * 일반적으로 세션에 저장되는 키는 대문자로 입력하며, 앞에 SS를 붙인다.
                 *
                 * Session 단어에서 SS를 가져온 것이다.
                 */
                session.setAttribute("SS_EMAIL_USER", email_user);
                log.info((String) session.getAttribute("SS_EMAIL_USER"));
                msg ="로그인 성공";
                url="/market/index";
            }else{
                msg ="로그인 실패";
                url="/login/loginUser";
            }

        } catch (Exception e) {
            //저장이 실패되면 사용자에게 보여줄 메시지
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".insertUserInfo end!");

            /* 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
             * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이  있어
             * 문자 유형(String)으로 강제 형변환하여 jsp에 전달한다.
             * */
            model.addAttribute("msg",msg);
            model.addAttribute("url",url);
            model.addAttribute("res", String.valueOf(res));

            //변수 초기화(메모리 효율화 시키기 위해 사용함)
            pDTO = null;

        }

        return "/redirect";
    }






}
