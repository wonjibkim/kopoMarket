package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.PageDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "notice")
public class NoticeController {

    @Resource(name = "NoticeService")
    private INoticeService noticeService;

    @GetMapping(value = "noticeboard")
    public String noticeboard() {
        log.info(this.getClass().getName() + "noticeboard start");

        log.info(this.getClass().getName() + "noticeboard end");

        return "/notice/NoticeBoard";
    }

    @GetMapping(value = "noticeboardlist")
    public String NoticeList(ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + ".NoticeList start!");

        List<NoticeDTO> rList = noticeService.getNoticeList();

        if (rList == null) {
            rList = new ArrayList<>();

        }
        for (int i = 0; i < rList.size(); i++) {
            NoticeDTO rDTO = rList.get(i);
            log.info(rDTO.getNotice_seq());
            log.info(rDTO.getEmail());
            log.info(rDTO.getTitle());
            log.info(rDTO.getContents());
            }

        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".NoticeList end!");

        return "/notice/NoticeBoardList";

    }

    @PostMapping(value = "/insertBoard")
    public String insertboard(HttpServletRequest request, ModelMap model){
        log.info(this.getClass().getName()+"insertboard start");
        String msg ="";

        try{
            String email = CmmUtil.nvl(request.getParameter("email"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String contents = CmmUtil.nvl(request.getParameter("contents"));

            log.info("email : " + email);
            log.info("title : " + title);
            log.info("contents : " + contents);

            NoticeDTO pDTO = new NoticeDTO();

            pDTO.setEmail(email);
            pDTO.setTitle(title);
            pDTO.setContents(contents);

            noticeService.InsertNoticeInfo(pDTO);

            msg = "등록되었습니다.";


        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeInsert end!");
            model.addAttribute("msg", msg);

        }
        log.info(this.getClass().getName()+"insertboard end");
        return "/notice/MsgToList";
    }

    @GetMapping(value = "/noticeinfo")
    public String NoticeInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".NoticeInfo start!");

        String msg = "";

        try {

            log.info("실행 좀 되라");
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

            log.info("nSeq : " + nSeq);

            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setNotice_seq(nSeq);

           NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO);

            if (rDTO == null) {
                rDTO = new NoticeDTO();

            }

            log.info("getNoticeInfo success!!!");

            model.addAttribute("rDTO", rDTO);


        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeInsert end!");

            model.addAttribute("msg", msg);

        }

        log.info(this.getClass().getName() + ".NoticeInfo end!");

        return "/notice/NoticeInfo";
    }


}
