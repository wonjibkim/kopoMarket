package kopo.poly.service;

import kopo.poly.dto.MailDTO;

import java.util.Map;

public interface IMailService {

    //이메일 인증코드
    int doSendMail(MailDTO mDTO);

    Map<String, String> doSendPwdCode(MailDTO mDTO);

    int insertMailCode(MailDTO mDTO) throws Exception;

}
