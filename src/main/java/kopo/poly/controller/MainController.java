package kopo.poly.controller;

import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.RecipeDTO;
import kopo.poly.paging.Criteria;
import kopo.poly.paging.Paging;
import kopo.poly.service.IMainService;
import kopo.poly.service.IPageService;
import kopo.poly.service.IRecipeService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "market")
public class MainController {
    final private String FILE_UPLOAD_SAVE_PATH = "C:/Users/data18/Desktop/Coding/Market/src/main/resources"; //폴더에 저장


    @Resource(name = "MainService")
    private IMainService mainService;
    @Resource(name = "RecipeService")
    private IRecipeService RecipeService;

    @Resource(name = "PageService")
    private IPageService pageService;



    /** !!!!!!!!!!!!!!1 메인페이지  1!!!!!!!!!!!!!!!!!!! */

    @GetMapping(value = "FoodDetail")
    public String FoodDetail(HttpServletRequest request, ModelMap model){
        log.info(this.getClass().getName() + ".FoodDetail start!");

        String msg = "";

        try {

            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

            log.info("nSeq : " + nSeq);

            FoodDTO pDTO = new FoodDTO();
            pDTO.setP_num(nSeq);

            FoodDTO rDTO = mainService.getFoodInfo(pDTO);

            if (rDTO == null) {
                rDTO = new FoodDTO();
            }

            log.info("getFoodInfo success!!!");

            model.addAttribute("rDTO", rDTO);
            List<RecipeDTO> lList = RecipeService.getRecipe( CmmUtil.nvl(rDTO.getP_name()));
            for (int i = 0; i < lList.size(); i++) {
                RecipeDTO lDTO = lList.get(i);
                log.info(lDTO.getRecipe_name());
            }
            model.addAttribute("lList",lList);


        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".getFoodInfo end!");

            model.addAttribute("msg", msg);

        }

        log.info(this.getClass().getName() + ".getFoodInfo end!");

        return "/market/FoodDetail";
    }
    @GetMapping(value = "RecipeDetail")
    public String RecipeDetail(HttpServletRequest request, ModelMap model){
        log.info(this.getClass().getName() + ".RecipeDetail start!");

        String msg = "";

        try {

            String Recipe_name = CmmUtil.nvl(request.getParameter("Recipe_name"));

            log.info("Recipe_name : " + Recipe_name);


            log.info("getRecipeInfo success!!!");

            List<RecipeDTO> lList = RecipeService.getRecipeInfo(Recipe_name);
            log.info(String.valueOf(lList.size()));
            for (int i = 0; i < lList.size(); i++) {
                RecipeDTO lDTO = lList.get(i);
                log.info("레시피 이름 : "+lDTO.getRecipe_name());
                log.info("레시피 재료 : "+lDTO.getIngredient());
            }
            model.addAttribute("lList",lList);


        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".getRecipeInfo end!");

            model.addAttribute("msg", msg);

        }

        log.info(this.getClass().getName() + ".getRecipeInfo end!");

        return "/market/RecipeDetail";
    }

    @GetMapping(value = "index")
    public String index(ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".FoodList start!");

        List<FoodDTO> rList = mainService.getFoodList();

        if (rList == null) {
            rList = new ArrayList<>();

        }
        for (int i = 0; i < rList.size(); i++) {
            FoodDTO rDTO = rList.get(i);
            log.info(rDTO.getP_num());
            log.info(rDTO.getP_name());
            log.info(rDTO.getP_price());
            log.info(rDTO.getP_info());
            log.info(rDTO.getP_category());
        }

        List<RecipeDTO > nList = RecipeService.getRecipeName();
        for (int i=0; i<nList.size(); i++){
            RecipeDTO nDTO = nList.get(i);
            log.info(nDTO.getRecipe_name());
            log.info(nDTO.getFilename());
        }

        // 조회 결과를 JSP에 전달하기
        model.addAttribute("nList", nList);


        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".NoticeList end!");

        return "/market/IndexMain";

    }

    @GetMapping(value = "/ShopList")
    public String ShopList(Criteria cri, ModelMap model, HttpServletRequest request) throws Exception {

        List<FoodDTO> rList = mainService.getFoodList();

        if (rList == null) {
            rList = new ArrayList<>();

        }
        for (int i = 0; i < rList.size(); i++) {
            FoodDTO rDTO = rList.get(i);
            log.info(rDTO.getP_num());
            log.info(rDTO.getP_name());
            log.info(rDTO.getP_price());
            log.info(rDTO.getP_info());
            log.info(rDTO.getP_category());
        }
        model.addAttribute("rList", rList);
        String keyword =request.getParameter("keyword");
        cri.setKeyword(keyword);
        log.info("cri : "+cri);
        log.info("검색어 : "+keyword);

        // 전체 글 개수
        if(keyword=="" || keyword==null) {
            int boardListCnt = pageService.ShopListCnt();

            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

            List<Map<String, Object>> list = pageService.ShopList(cri);

            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }else{
            int boardListCnt = pageService.ShopSearchCnt(keyword);

            log.info("total : "+boardListCnt);
            // 페이징 객체
            Paging paging = new Paging();
            paging.setCri(cri);
            paging.setTotalCount(boardListCnt);

            log.info("페이징 : "+paging);
            log.info("검색 1");
            List<Map<String, Object>> list = pageService.ShopSearchList(cri);


            model.addAttribute("list", list);
            model.addAttribute("paging", paging);
            log.info("list" + list);
            log.info("paging" + paging);
        }

        return "/market/ShopList";
    }








}






