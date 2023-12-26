package kopo.poly.service.impl;


import kopo.poly.dto.MailDTO;
import kopo.poly.persistance.mapper.IUserInfoMapper;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.MailCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service("MailService")
public class MailService implements IMailService {

    private final IUserInfoMapper userInfoMapper;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail; //fromMail 상수 선언


    @Override
    public int insertMailCode(MailDTO mDTO) throws Exception{

        log.info(this.getClass().getName()+" insertMailCode start1!!");
        log.info(this.getClass().getName()+" insertMailCode ejnddd!!");


        return userInfoMapper.insertMailCode(mDTO);
    }

    @Override
    public int doSendMail(MailDTO mDTO) {

        log.info(this.getClass().getName()+".doSendMail start!!");
        //메일 발송 성공여부(발송여부:1/ 발송실패:0)
        int res = 1;

        //전달 받은 DTO로부터 데이터 가져오기 ( DTO객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 때문에 에러방지차원으로 if문 사용함
        if (mDTO == null){
            mDTO = new MailDTO();
        }

        String toEmail = CmmUtil.nvl(mDTO.getToEmail()); //받는 사람
        String emailCode = mDTO.getMail_code(); //메일 인증 코드



        log.info("toEmail: " + toEmail);
        log.info("emailCode : " + emailCode);

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");


        try {

            message.setSubject("POLY MARKET 인증번호가 도착했습니다. "); // 제목
            String msgg= "";
            msgg+= "<div style = 'margin:100px;' >" ;
            msgg+= "<h1> 안녕하세요 Poly Market 입니다 ! </h1> ";
            msgg+= "<br>";
            msgg+= "<p> 아래 코드를 회원가입 창으로 돌아가 입력해주세요 </p>";
            msgg+= "<br>";
            msgg+= "<p> 감사합니다 </p>";
            msgg+= "<br>";
            // msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
            msgg+= "<h3 style='color:blue;'> 회원가입 코드입니다. </h3>";
            msgg+= "<div style='font-size:130%'>";
            msgg+= "CODE : <strong>";
            msgg+= emailCode + "</strong> <br> <div><br>";
            // msgg+= "</div>";

            message.setText(msgg, "utf-8", "html");
            message.setFrom(fromMail);



            messageHelper.setTo(toEmail); // 받는사람
            messageHelper.setFrom(fromMail); //보내는 사람
            // messageHelper.setText(emailCode);// 이메일 코드 전송


            //messageHelper.setSubject(title); // 메일제목
            //messageHelper.setText(contents); // 메일 내용



            mailSender.send(message);
        } catch (Exception e) {
            res = 0; //메일 발송이 실패하기 때문에 0으로 변경
            log.info("[ERROR] " + this.getClass().getName()+ ".doSendMail : " + e );
        }
        log.info(this.getClass().getName()+".doSendMail end!!");


        return res;
    }

    @Override
    public Map<String, String> doSendPwdCode(MailDTO mDTO) {
        log.info(this.getClass().getName()+".doSendMail start!!");
        //메일 발송 성공여부(발송여부:1/ 발송실패:0)
        int res = 1;

        //전달 받은 DTO로부터 데이터 가져오기 ( DTO객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 때문에 에러방지차원으로 if문 사용함
        if (mDTO == null){
            mDTO = new MailDTO();
        }

        String toEmail = CmmUtil.nvl(mDTO.getToEmail()); //받는 사람
        String emailCode = CmmUtil.nvl(mDTO.getMail_code()); //메일 인증 코드
        log.info("toEmail: " + toEmail);
        log.info("emailCode : " + emailCode);


        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");


        try {

            message.setSubject("POLY MARKET 비밀번호 찾기 인증번호가 도착했습니다. "); // 제목
            String msgg= "";
            msgg+= "<div style = 'margin:100px;' >" ;
            msgg+= "<h1> 안녕하세요 Poly Market 입니다 ! </h1> ";
            msgg+= "<br>";
            msgg+= "<p> 아래 코드를 비밀번호 찾기 창으로 돌아가 코드를 입력해주세요 </p>";
            msgg+= "<br>";
            msgg+= "<p> 감사합니다 </p>";
            msgg+= "<br>";
            // msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
            msgg+= "<h3 style='color:blue;'> 비밀번호 찾기 인증코드입니다.\n 돌아가서 코드를 입력해주세요 </h3>";
            msgg+= "<div style='font-size:130%'>";
            msgg+= "CODE : <strong>";
            msgg+= emailCode + "</strong> <br> <div><br>";
            // msgg+= "</div>";

            message.setText(msgg, "utf-8", "html");
            message.setFrom(fromMail);



            messageHelper.setTo(toEmail); // 받는사람
            messageHelper.setFrom(fromMail); //보내는 사람
            // messageHelper.setText(emailCode);// 이메일 코드 전송


            //messageHelper.setSubject(title); // 메일제목
            //messageHelper.setText(contents); // 메일 내용



            mailSender.send(message);
        } catch (Exception e) {
            res = 0; //메일 발송이 실패하기 때문에 0으로 변경
            log.info("[ERROR] " + this.getClass().getName()+ ".doSendMail : " + e );
        }
        log.info(this.getClass().getName()+".doSendMail end!!");


        Map<String, String> map = new HashMap<String, String>();
        map.put("emailCode", emailCode);
        map.put("res", String.valueOf(res)); /* res 스트링으로 형변환*/
        log.info("res : " + res);

        return map;
    }


}
