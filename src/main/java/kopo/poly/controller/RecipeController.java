package kopo.poly.controller;

import kopo.poly.service.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


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
    public String getRecipe(ModelMap model)
            throws Exception {

        return "/recipe/recipeList";
    }

}



