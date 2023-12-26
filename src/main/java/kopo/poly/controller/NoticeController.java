package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.paging.Criteria;
import kopo.poly.paging.Paging;
import kopo.poly.service.INoticeService;
import kopo.poly.service.IPageService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "notice")
public class NoticeController {

    @Resource(name = "NoticeService")
    private INoticeService noticeService;

    @Resource(name = "PageService")
    private IPageService pageService;

    @GetMapping(value = "noticeboard")
    public String noticeboard() {
        log.info(this.getClass().getName() + "noticeboard start");

        log.info(this.getClass().getName() + "noticeboard end");

        return "/notice/NoticeBoard";
    }

    @GetMapping(value = "noticeboardlist")
    public String NoticeList(Criteria cri, ModelMap model,HttpServletRequest request)
            throws Exception {

        log.info(this.getClass().getName() + ".NoticeList start!");
        String keyword = request.getParameter("keyword");
        cri.setKeyword(keyword);
        log.info("cri : "+cri);
        log.info("검색어 : "+keyword);

        // 전체 글 개수
        if(keyword=="" || keyword==null) {
            int boardListCnt = pageService.noticeListCnt();

            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

            List<Map<String, Object>> list = pageService.noticeList(cri);

            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }else{
            int boardListCnt = pageService.noticeSearchCnt(keyword);

            log.info("total : "+boardListCnt);
            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

            log.info("페이징 : "+paging);
            log.info("검색 1");
            List<Map<String, Object>> list = pageService.noticeSearchList(cri);


            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }

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

    @RequestMapping(value="/getBoardList.do")
    public String getBoardList(Model model) throws Exception {

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
    @GetMapping(value = "/noticeboardModify")
    public String noticeboardModify(HttpServletRequest request,ModelMap model) throws Exception {
        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));
        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(nSeq);

        NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO);

        if (rDTO == null) {
            rDTO = new NoticeDTO();
        }

        log.info("getNoticeInfo success!!!");

        model.addAttribute("rDTO", rDTO);
        return "/notice/NoticeEditInfo";
    }
    @GetMapping(value = "/noticeboardDelete")
    public String noticeboardDelete(HttpServletRequest request,ModelMap model) throws Exception {

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        log.info("게시물 번호 : "+nSeq);
        noticeService.noticeDelete(nSeq);
        String url="";
        url =  "/notice/noticeboardlist";
        String msg="삭제성공";

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        return "/redirect";
    }

    @PostMapping(value = "/updateBoard")
    public String noticeUpdate(HttpServletRequest request,ModelMap model) throws Exception {

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));
        NoticeDTO nDTO = new NoticeDTO();
        nDTO.setNotice_seq(nSeq);
        nDTO.setTitle(request.getParameter("title"));
        nDTO.setContents(request.getParameter("contents"));
        noticeService.noticeUpdate(nDTO);
        String url="";
        url =  "/notice/noticeboardlist";
        String msg="수정성공";
        model.addAttribute("url",url);
        model.addAttribute("msg",msg);
        return "/redirect";
    }
}
