package kopo.poly.controller;

import kopo.poly.dto.FoodDTO;
import kopo.poly.service.IMainService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "market")
public class MainController {
    final private String FILE_UPLOAD_SAVE_PATH = "c:/upload"; // C:\\upload 폴더에 저장


    @Resource(name = "MainService")
    private IMainService mainService;


    /** !!!!!!!!!!!!!!1 메인페이지  1!!!!!!!!!!!!!!!!!!! */
    @GetMapping(value = "index")
    public String index(){
        log.info(this.getClass().getName() + "index start ");
        log.info(this.getClass().getName() + "index end" );
        return "/market/IndexMain";
    }





    /** !!!!!!!!!!!!!!!!!!! 음식등록 !!!!!!!!!!!!!!!! */
    @GetMapping(value = "/insert")
    public String foodinsrt() {
        log.info(this.getClass().getName() + "foodinsrt start");
        log.info(this.getClass().getName() + "foodinsrt end");

        return "/market/FoodInsert";
    }

    /** !!!!!!!!!!!!!!!!!!! 음식등록 실행 !!!!!!!!!!!!!!!! */
    @PostMapping(value = "/insert_St")
    public String insert_St(HttpServletRequest request, HttpSession session, ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf)
    {
        log.info(this.getClass().getName() + "insert_St start");

        String msg ="";
        String url ="/market/insert";


        try {
//           마트 사용자 pk 정보 세션으로 받기
//            String user_id = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));



      /////////////////////////////////////////////////////////////////////////////////

            // mf.getOriginalFilename()은 웹페이지에서 클라이언트가 보낸 File의 이름을 가져온다.
            // Ex) 클라이언트가 사과.jpg를 업로드하면 mf.getOriginalFilename() = 사과.jpg
            String originalFileName = mf.getOriginalFilename();

            // 파일 확장자 가져오기(파일 확장자를 포함한 전체 이름(myimage.jpg)에서 뒤쪽부터 .이 존재하는 위치 찾기
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1,
                    originalFileName.length()).toLowerCase();

            // 시분초로 파일명을 만들기 때문에 파일명이 안겹친다.
            // EX) 162235_jpg
            String saveFileName = DateUtil.getDateTime("HHmmss") + "." + ext;

            // 웹서버에 업로드한 파일 저장하는 물리적 경로
            // c 밑에 upload 밑에 새로 생기는 폴더의 경로 저장
            // EX) C:/upload/2022_10_07
            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);


            // EX) C:/upload/2022_10_07/162235_jpg
            String fullFileInfo = saveFilePath + "/"+ saveFileName;


            // 업로드 되는 파일을 서버에 저장
            mf.transferTo(new File(fullFileInfo));

            String p_name = CmmUtil.nvl(request.getParameter("p_name"));
            String p_price = CmmUtil.nvl(request.getParameter("p_price"));
            String p_sell = CmmUtil.nvl(request.getParameter("p_sell"));
            String p_info = CmmUtil.nvl(request.getParameter("p_info"));
            String p_period = CmmUtil.nvl(request.getParameter("p_period"));
            String p_category = CmmUtil.nvl(request.getParameter("p_category"));

            String p_discount = CmmUtil.nvl(request.getParameter("p_discount"));
            String p_ancestry = CmmUtil.nvl(request.getParameter("p_ancestry"));
            String p_weight = CmmUtil.nvl(request.getParameter("p_weight"));


            log.info("p_name : " + p_name);
            log.info("p_price : " + p_price);
            log.info("p_sell : " + p_sell);
            log.info("p_info : " + p_info);
            log.info("p_period : " + p_period);
            log.info("p_category : " + p_category);

            log.info("p_discount : " + p_discount);
            log.info("p_ancestry : " + p_ancestry);
            log.info("p_weight : " + p_weight);

            // FoodDTO 객체 생성
            FoodDTO fDTO = new FoodDTO();

            fDTO.setP_name(p_name);
            fDTO.setP_price(p_price);
            fDTO.setP_sell(p_sell);
            fDTO.setP_info(p_info);
            fDTO.setP_period(p_period);
            fDTO.setP_category(p_category);
            fDTO.setP_fileName(saveFileName); // 저장되는 파일명
            fDTO.setP_filePath(saveFilePath);

            fDTO.setP_discount(p_discount);
            fDTO.setP_ancestry(p_ancestry);
            fDTO.setP_weight(p_weight);

            //////////////////////////////////////////////////////

            if(fDTO == null) {
                fDTO = new FoodDTO();
                log.info("fDTO is null !!");
            }

            // 정상적으로 값이 생성되었는지 로그 찍어서 확인
            log.info("p_name : " + fDTO.getP_name());
            log.info("p_price : " + fDTO.getP_price());
            log.info("p_sell : " + fDTO.getP_sell());
            log.info("p_info : " + fDTO.getP_info());
            log.info("p_period : " + fDTO.getP_period());
            log.info("p_category : " + fDTO.getP_category());
            log.info("p_saveFileName : " + saveFileName);
            log.info("p_saveFilePath : " + saveFilePath);

            log.info("p_discount : " + p_discount);
            log.info("p_ancestry : " + p_ancestry);
            log.info("p_weight : " + p_weight);

            mainService.InsertFood(fDTO);




            msg = "등록되었습니다.";





        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeInsert end!");



            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }

        log.info(this.getClass().getName() + "insert_St end");

        return "/redirect";
    }




    @GetMapping(value = "sell_main") //  판매자 음식 메인페이지
    public String sell_main(Model model)throws Exception{

        log.info(getClass().getName()+"sell_main_st start");

        List<FoodDTO> rList = mainService.getFoodList();

        if(rList == null){
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        log.info(getClass().getName()+"sell_main_st end");

        model.addAttribute("rList",rList);

        return "/market/prsell";
    }




    @GetMapping(value = "/foodsell_up") // 음식 등록 수정
    public String foodsell_up(Model model) {
        log.info(this.getClass().getName() + "foodsell_up start");

        List<FoodDTO> rList = mainService.getFoodList();

        if(rList == null){
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        model.addAttribute("rList",rList);


        log.info(this.getClass().getName() + "foodsell_up end");

        return "/market/prsell_up";
    }






    @PostMapping(value = "/foodsell_up_st") // 음식 등록수정 실행
    public String foodsell_up_st(HttpServletRequest request, HttpSession session, ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf)
    {
        log.info(this.getClass().getName() + "foodsell_up_st start");

        String msg ="";
        String url ="/market/insert";


        try {


//           마트 사용자 pk 정보 세션으로 받기
//            String user_id = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));



            /////////////////////////////////////////////////////////////////////////////////

            // mf.getOriginalFilename()은 웹페이지에서 클라이언트가 보낸 File의 이름을 가져온다.
            // Ex) 클라이언트가 사과.jpg를 업로드하면 mf.getOriginalFilename() = 사과.jpg
            String originalFileName = mf.getOriginalFilename();

            // 파일 확장자 가져오기(파일 확장자를 포함한 전체 이름(myimage.jpg)에서 뒤쪽부터 .이 존재하는 위치 찾기
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1,
                    originalFileName.length()).toLowerCase();

            // 시분초로 파일명을 만들기 때문에 파일명이 안겹친다.
            // EX) 162235_jpg
            String saveFileName = DateUtil.getDateTime("HHmmss") + "." + ext;

            // 웹서버에 업로드한 파일 저장하는 물리적 경로
            // c 밑에 upload 밑에 새로 생기는 폴더의 경로 저장
            // EX) C:/upload/2022_10_07
            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);


            // EX) C:/upload/2022_10_07/162235_jpg
            String fullFileInfo = saveFilePath + "/"+ saveFileName;


            // 업로드 되는 파일을 서버에 저장
            mf.transferTo(new File(fullFileInfo));

            String p_name = CmmUtil.nvl(request.getParameter("p_name"));
            String p_price = CmmUtil.nvl(request.getParameter("p_price"));
            String p_sell = CmmUtil.nvl(request.getParameter("p_sell"));
            String p_info = CmmUtil.nvl(request.getParameter("p_info"));
            String p_period = CmmUtil.nvl(request.getParameter("p_period"));
            String p_category = CmmUtil.nvl(request.getParameter("p_category"));

            String p_discount = CmmUtil.nvl(request.getParameter("p_discount"));
            String p_ancestry = CmmUtil.nvl(request.getParameter("p_ancestry"));
            String p_weight = CmmUtil.nvl(request.getParameter("p_weight"));


            log.info("p_name : " + p_name);
            log.info("p_price : " + p_price);
            log.info("p_sell : " + p_sell);
            log.info("p_info : " + p_info);
            log.info("p_period : " + p_period);
            log.info("p_category : " + p_category);

            log.info("p_discount : " + p_discount);
            log.info("p_ancestry : " + p_ancestry);
            log.info("p_weight : " + p_weight);

            // FoodDTO 객체 생성
            FoodDTO fDTO = new FoodDTO();

            fDTO.setP_name(p_name);
            fDTO.setP_price(p_price);
            fDTO.setP_sell(p_sell);
            fDTO.setP_info(p_info);
            fDTO.setP_period(p_period);
            fDTO.setP_category(p_category);
            fDTO.setP_fileName(saveFileName); // 저장되는 파일명
            fDTO.setP_filePath(saveFilePath);

            fDTO.setP_discount(p_discount);
            fDTO.setP_ancestry(p_ancestry);
            fDTO.setP_weight(p_weight);

            //////////////////////////////////////////////////////

            if(fDTO == null) {
                fDTO = new FoodDTO();
                log.info("fDTO is null !!");
            }

            // 정상적으로 값이 생성되었는지 로그 찍어서 확인
            log.info("p_name : " + fDTO.getP_name());
            log.info("p_price : " + fDTO.getP_price());
            log.info("p_sell : " + fDTO.getP_sell());
            log.info("p_info : " + fDTO.getP_info());
            log.info("p_period : " + fDTO.getP_period());
            log.info("p_category : " + fDTO.getP_category());
            log.info("p_saveFileName : " + saveFileName);
            log.info("p_saveFilePath : " + saveFilePath);

            log.info("p_discount : " + p_discount);
            log.info("p_ancestry : " + p_ancestry);
            log.info("p_weight : " + p_weight);

            mainService.InsertFood(fDTO);




            msg = "수정되었습니다.";





        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".NoticeInsert end!");



            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }

        log.info(this.getClass().getName() + "foodsell_up_st end");

        return "/redirect";
    }










}






