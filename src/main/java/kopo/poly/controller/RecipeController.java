package kopo.poly.controller;

import kopo.poly.dto.RecipeDTO;
import kopo.poly.service.IRecipeService;
import kopo.poly.service.impl.RecipeService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Controller
@RequestMapping(value = "recipe")
public class RecipeController {


    @Resource(name = "RecipeService")
    private IRecipeService RecipeService;

    /**
     * CGV 영화 수집을 위한 URL 호출
     */
    @GetMapping(value = "collectRecipe")
    public String collectMovieRank(ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + ".collectRecipe Start!");

        int res = RecipeService.collectRecipe();

        //크롤링 결과를 넣어주기
        model.addAttribute("res", String.valueOf(res));

        log.info(this.getClass().getName() + ".collectRecipe End!");

        return "/recipe/RecipeCollect";
    }


    @GetMapping(value = "getRecipe")
    public String getRecipe(HttpServletRequest request, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + ".getrecipe Start!");

        // 가져올 날짜 파라미터로 받기
        // 파라미터의 값이 없다면, 오늘 날짜로 데이터 받기
        String collectTime = CmmUtil.nvl(request.getParameter("collectTime"), DateUtil.getDateTime("yyyyMMdd"));

        log.info("collectTime : " + collectTime); // 반드시 로그 찍자!

        RecipeDTO pDTO = new RecipeDTO ();

        List<RecipeDTO > rList = RecipeService.getRecipeInfo(pDTO);

        // 조회 결과를 JSP에 전달하기
        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".getrecipe End!");

        return "/recipe/recipeList";
    }

}



