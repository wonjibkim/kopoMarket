package kopo.poly.controller;

import kopo.poly.paging.Criteria;
import kopo.poly.paging.Paging;
import kopo.poly.service.IPageService;
import kopo.poly.service.IPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("price")
public class PriceController {
    @Resource(name = "PriceService")
    private IPriceService priceService;

    @Resource(name = "PageService")
    private IPageService pageService;


    @GetMapping(value = "list")
    public String Pricelist(Criteria cri, ModelMap model, HttpServletRequest request)throws Exception{

        log.info(getClass().getName()+"Pricelist start");

        String keyword = request.getParameter("keyword");
        cri.setKeyword(keyword);
        log.info("cri : "+cri);
        log.info("검색어 : "+keyword);

        // 전체 글 개수
        if(keyword=="" || keyword==null) {

//            int boardListCnt = pageService.noticeListCnt();
            int boardListCnt = pageService.priceListCnt();

            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

//            List<Map<String, Object>> list = pageService.noticeList(cri);
            List<Map<String, Object>> list = pageService.priceList(cri);

            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }else{
//            int boardListCnt = pageService.noticeSearchCnt(keyword);
            int boardListCnt = pageService.priceSearchCnt(keyword); //바꾸기

            log.info("total : "+boardListCnt);
            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

            log.info("페이징 : "+paging);
            log.info("검색 1");
//            List<Map<String, Object>> list = pageService.noticeSearchList(cri);
            List<Map<String, Object>> list = pageService.priceSearchList(cri);


            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }





        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

//        List<priceDTO> rList = priceService.getprice();
//
//        for (int i = 0; i <rList.size() ; i++) {
//
//            priceDTO mDTO = rList.get(i);
//            log.info(mDTO.getPrdlst_nm());
//            log.info(mDTO.getExamin_unit());
//            log.info(mDTO.getAmt());
//            log.info(mDTO.getArea_nm());
//
//        }
//
//        model.addAttribute("rList",rList);
//
//        log.info(getClass().getName()+"Pricelist end");

        return "/market/pricelist2";
    }

}

