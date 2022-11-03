package kopo.poly.service.impl;

import kopo.poly.dto.RecipeDTO;
import kopo.poly.persistance.mapper.IRecipeMapper;
import kopo.poly.service.IRecipeService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("RecipeService")
public class RecipeService implements IRecipeService {

    // RequiredArgsConstructor 어노테이션으로 생성자를 자동 생성함
    // movieMapper 변수에 이미 메모리에 올라간 Mapper 객체를 넣어줌
    // 예전에는 autowired 어노테이션를 통해 설정했었지만, 이젠 생성자를 통해 객체 주입함
    private final IRecipeMapper RecipeMapper;

    /**
     * JSOUP 라이브러리를 통한 CGV 영화 순위 정보가져오기
     */
    @Transactional
    @Override
//    @Scheduled(cron = "5 * * * * *")
    public int collectRecipe() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".collectRecipe Start!");

        int res = 0; //크롤링 결과 (0보다 크면 크롤링 성공)
        int i = 100;
        // CGV 영화 순위 정보 가져올 사이트 주소
        String url = "https://2bob.co.kr/recipe.php?id=view&idx=";
        RecipeDTO pDTO = null;
        while (true) {
            // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
            Document doc = null; //
            String path ="c:/upload";
            //사이트 접속(http프로토롱만 가능, https 프로토콜은 보안상 안됨)
            doc = Jsoup.connect(url + i).get();
            if (i==110) {
                break;
            } else {
                Elements element = doc.select("div.rec_exp");

                log.info(String.valueOf(element.select("h2.prod_title")));

                Elements recipe_name = element.select("h2.prod_title");
                element = doc.select("div.text_box");
                Elements ingredient = element.select("p.mate_list");

                pDTO = new RecipeDTO();
                pDTO.setRecipe_name(CmmUtil.nvl(recipe_name.text().trim()));
                pDTO.setIngredient(CmmUtil.nvl(ingredient.text().trim()));

                log.info(CmmUtil.nvl(ingredient.text().trim()));
                log.info(recipe_name.text().trim());

//                element = doc.select("div.rec_view_top clr");
//                log.info(element.select("div.fl rec_view_img").attr("abs:src"));
//                String img = element.select("div.fl rec_view_img").attr("abs:src");
//                String filename = img.substring(img.lastIndexOf("/")+1);
//
//                HttpURLConnection conn = null;
//
//                URL imgUrl;
//
//                imgUrl = new URL(img);
//
//                conn = (HttpURLConnection) imgUrl.openConnection();
//                conn.setRequestProperty("Referer","https://2bob.co.kr/");
//                log.info("Img URL : "+img);
//
//                BufferedImage buffImg = ImageIO.read(conn.getInputStream());
//                FileOutputStream file = new FileOutputStream(path + filename + ".jpg");
//                ImageIO.write(buffImg, "jpg",file);
//                pDTO.setFilename(filename);
                res += RecipeMapper.InsertRecipeInfo(pDTO);
                i++;
            }
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".collectMovieRank End!");

        return res;
    }

    @Override
    public List<RecipeDTO> getRecipeInfo(RecipeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getMovieInfo Start!");

        // DB에서 조회하기
        List<RecipeDTO > rList = RecipeMapper.getRecipeInfo(pDTO);

        // DB 조회 결과가 없다면, NullPointer 에러 방지를 위해
        // 데이터가 존재하는 않는 객체로 메모리에 올리기
        if (rList == null) {
            rList = new ArrayList<>();
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getMovieInfo End!");

        return rList;
    }
}
