package kopo.poly.controller;

import kopo.poly.dto.*;
import kopo.poly.service.IMarketService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
public class MarketController {

    final private String FILE_UPLOAD_SAVE_PATH = "C:/Users/data18/Desktop/Coding/kopoMarket/src/main/resources/static"; // C:\\upload 폴더에 저장

    @Resource(name = "MarketService") //서비스 메모리에 올리기
    private IMarketService marketService;




    /**
     * !!!!!!!!!!!!!!!!!!! 음식등록 !!!!!!!!!!!!!!!!
     */
    @GetMapping(value = "/FoodReg")
    public String FoodReg() {
        log.info(this.getClass().getName() + "FoodReg start");
        log.info(this.getClass().getName() + "FoodReg end");

        return "/market/FoodReg";
    }



    /**
     * !!!!!!!!!!!!!!!!!!! 음식등록 실행 !!!!!!!!!!!!!!!!
     */
    @PostMapping(value = "/FoodInsert")
    public String FoodInsert(HttpServletRequest request, HttpSession session, ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf) {
        log.info(this.getClass().getName() + "FoodInsert start");

        String msg = "";
        String url = "/market/FoodList";


        try {






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
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            String makret_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));


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
            String p_barcode = CmmUtil.nvl(request.getParameter("p_barcode"));


            log.info("p_name : " + p_name);
            log.info("p_price : " + p_price);
            log.info("p_sell : " + p_sell);
            log.info("p_info : " + p_info);
            log.info("p_period : " + p_period);
            log.info("p_category : " + p_category);

            log.info("p_discount : " + p_discount);
            log.info("p_ancestry : " + p_ancestry);
            log.info("p_weight : " + p_weight);
            log.info("barcode : " + p_barcode);

            // FoodDTO 객체 생성
            FoodDTO fDTO = new FoodDTO();

            fDTO.setP_name(p_name);
            fDTO.setP_price(p_price);
            fDTO.setP_sell(p_sell);
            fDTO.setP_info(p_info);
            fDTO.setP_period(p_period);
            fDTO.setP_category(p_category);
            fDTO.setP_fileName(saveFileName); // 저장되는 파일명
            fDTO.setP_filePath(saveFilePath.replaceAll(FILE_UPLOAD_SAVE_PATH,""));

            fDTO.setP_discount(p_discount);
            fDTO.setP_ancestry(p_ancestry);
            fDTO.setP_weight(p_weight);
            fDTO.setMarket_seq(makret_seq);
            fDTO.setP_barcode(p_barcode);

            //////////////////////////////////////////////////////

            if (fDTO == null) {
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

            marketService.FoodInsert(fDTO);


            msg = "등록되었습니다.";


        } catch (Exception e) {

            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".FoodInsert end!");


            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }


        return "/redirect";
    }


    /**
     * !!!!!!!!!!!!!!!!!!! 판매자 재고 게시판 !!!!!!!!!!!!!!!!
     */
    @GetMapping(value = "FoodList")
    public String sell_main(Model model,HttpSession session) throws Exception {


        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기


        log.info(getClass().getName() + "FoodList start");

        List<FoodDTO> rList = marketService.FoodList(fDTO);

        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        log.info(getClass().getName() + "FoodList end");

        model.addAttribute("rList", rList);

        return "/market/FoodList";
    }


    /**
     * !!!!!!!!!!!!!!!!!!! 물품 상세보기 + 수정가능한 페이지  !!!!!!!!!!!!!!!!
     */

    @GetMapping(value = "/FoodEditInfo") // 리스트 수정보기
    public String FoodEditInfo(HttpServletRequest request, ModelMap model)throws Exception {

        log.info(this.getClass().getName() + ".FoodEditInfo start!");

            String p_num = CmmUtil.nvl(request.getParameter("p_num")); //물품 pk받기

            log.info("p_num : " + p_num);

            FoodDTO pDTO = new FoodDTO();
            pDTO.setP_num(p_num);

            FoodDTO rDTO = marketService.FoodEditInfo(pDTO);

            if (rDTO == null) {
                rDTO = new FoodDTO();

            }

            model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName() + "FoodEditInfo end!");

        return "/market/FoodEditInfo";


    }


    /**
     * !!!!!!!!!!!!!!!!!!! 물품 수정하기 !!!!!!!!!!!!!!!
     */
    @PostMapping(value = "/FoodEdit")
    public String foodUp(HttpServletRequest request, HttpSession session, ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf) {
        log.info(this.getClass().getName() + "FoodEdit start");

        String msg = "";
        String url = "/market/FoodList";


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
            String fullFileInfo = saveFilePath + "/" + saveFileName;


            // 업로드 되는 파일을 서버에 저장
            mf.transferTo(new File(fullFileInfo));



            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            String p_num = CmmUtil.nvl(request.getParameter("p_num")); //물품 pk받기

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

            fDTO.setP_num(p_num);
            fDTO.setP_name(p_name);
            fDTO.setP_price(p_price);
            fDTO.setP_sell(p_sell);
            fDTO.setP_info(p_info);
            fDTO.setP_period(p_period);
            fDTO.setP_category(p_category);
            fDTO.setP_fileName(saveFileName); // 저장되는 파일명
            fDTO.setP_filePath(saveFilePath.replaceAll(FILE_UPLOAD_SAVE_PATH,""));

            fDTO.setP_discount(p_discount);
            fDTO.setP_ancestry(p_ancestry);
            fDTO.setP_weight(p_weight);

            fDTO.setP_num(p_num);

            //////////////////////////////////////////////////////

            if (fDTO == null) {
                fDTO = new FoodDTO();
                log.info("fDTO is null !!");
            }

            // 정상적으로 값이 생성되었는지 로그 찍어서 확인
            log.info("p_num : " + p_num);
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

            marketService.foodUp(fDTO);


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

        log.info(this.getClass().getName() + "FoodEdit end");

        return "/redirect";
    }





    /**
     * !!!!!!!!!!!!!!!!!!! 물품 삭제하기 !!!!!!!!!!!!!!!
     */
    @PostMapping(value = "FoodDelete")
    @ResponseBody
    public List<FoodDTO> FoodDelete(HttpServletRequest request, ModelMap model,HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".FoodDelete start!");

        String msg = "";
        String url = "";

        String p_num = CmmUtil.nvl(request.getParameter("p_num")); // 글번호(PK)

        log.info("Pk키  : " + p_num);

        FoodDTO pDTO = new FoodDTO();

        pDTO.setP_num(p_num);

        marketService.FoodDelete(pDTO);

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기

        List<FoodDTO> fList = marketService.FoodList(fDTO);

        if (fList == null) {
            fList = new ArrayList<>();
        }

        return fList;
    }



    @GetMapping(value = "FoodListShelf") // 유통기한 지난 것들 게시판
    public String FoodListShelf(Model model,HttpSession session) throws Exception {

        log.info(this.getClass().getName() + "FoodListShelf start");
        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기

        log.info("마켓 seq " + fDTO.getMarket_seq() );


        List<FoodDTO> rList = marketService.FoodListShelf(fDTO);

        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        log.info(this.getClass().getName() + "FoodListShelf end");

        model.addAttribute("rList", rList);

        return "/market/FoodList_shelf";
    }




    /**
     * !!!!!!!!!!!!!!!!!!! 유통기한 지난 물품 일괄 삭제하기 !!!!!!!!!!!!!!!
     */

    @GetMapping(value = "FoodDeleteShelf")
    public String FoodDeleteShelf(ModelMap model,HttpSession session) throws Exception{

        log.info(this.getClass().getName() + ".FoodDeleteShelf start!");

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO mDTO = new FoodDTO();
        mDTO.setMarket_seq(market_seq);

        String msg = "";
        String url = "/market/FoodList";

        try {
            marketService.FoodDeleteShelf(mDTO);
            msg = "유통기한 지난 품목이 삭제되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".FoodDeleteShelf end!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
        }
        return "/redirect";
    }


    /**
     * !!!!!!!!!!!!!!!!!!! 유통기간 지난 식품 게시판에서  개별 삭제하기 !!!!!!!!!!!!!!!
     */
    @PostMapping(value = "FoodDeleteShelfLine")
    @ResponseBody
    public List<FoodDTO> FoodDeleteShelfLine(HttpServletRequest request,HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".FoodDelete start!");


        String p_num = CmmUtil.nvl(request.getParameter("p_num")); // 글번호(PK)

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);


        log.info("Pk키  : " + p_num);

        FoodDTO pDTO = new FoodDTO();

        pDTO.setP_num(p_num);

        marketService.FoodDelete(pDTO);


        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기
        List<FoodDTO> rList = marketService.FoodListShelf(fDTO);

        if (rList == null) {
            rList = new ArrayList<>();
        }

        return rList;
    }

    @GetMapping(value = "update_barcode")
    public String update_barcode()throws Exception{

        log.info(getClass().getName()+"update_barcode start");

        log.info(getClass().getName()+"update_barcode end");

        return "/market/update_subtract";
    }



    @PostMapping( value = "update_subtract") // 바코드 수량 하나 뺴기
    public String update_barcode(HttpServletRequest request,ModelMap model) throws Exception{
        log.info(getClass().getName() + "update_barcode Start");


        String msg="";
        String url="/market/update_barcode";

        try {
            String p_barcode = CmmUtil.nvl(request.getParameter("p_barcode")); //jsp에서 바코드 값 가져오기
            log.info("바코드 숫자"+ p_barcode);

            FoodDTO fDTO = new FoodDTO(); //바코드 값을 담기위한 dto 생성
            fDTO.setP_barcode(p_barcode); // 바코드 dto에 넣기

            marketService.update_barcode(fDTO);
            msg="상품수량이 -1이 빠졌습니다"; //alret창에 보여줄 메세지

        }catch (Exception e) { //에러가 있으면 보여줄 메세지

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".update_barcode end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url",url);

        }

        return "/redirect";
    }



    @GetMapping(value = "FoodListZero") // 재고 0 인 것 게시판
    public String FoodListZero(Model model,HttpSession session) throws Exception {

        log.info(this.getClass().getName() + "FoodListZero start");
        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기

        log.info("마켓 seq " + fDTO.getMarket_seq() );


        List<FoodDTO> rList = marketService.FoodListZero(fDTO);

        if (rList == null) {
            rList = new ArrayList<>();
            log.info("값이 들어오지 않음");
        }

        log.info(this.getClass().getName() + "FoodListZero end");

        model.addAttribute("rList", rList);

        return "/market/FoodList_zero";
    }




    @GetMapping(value = "Foodzerodel") // 재고 0 인 것 일괄 삭제
    public String Foodzerodel(ModelMap model,HttpSession session) throws Exception{

        log.info(this.getClass().getName() + ".FoodDeleteShelf start!");

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        FoodDTO mDTO = new FoodDTO();
        mDTO.setMarket_seq(market_seq);

        String msg = "";
        String url = "/market/FoodList";

        try {
            marketService.Foodzerodel(mDTO);
            msg = "재고가 없는 품목이 삭제되었습니다.";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".FoodDeleteShelf end!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
        }
        return "/redirect";
    }




    /**
     * !!!!!!!!!!!!!!!!!!! 유통기간 지난 식품 게시판에서  개별 삭제하기 !!!!!!!!!!!!!!!
     */
    @PostMapping(value = "FoodzerodelLine")
    @ResponseBody
    public List<FoodDTO> FoodzerodelLine(HttpServletRequest request,HttpSession session) throws Exception {

        log.info(this.getClass().getName() + ".FoodDelete start!");


        String p_num = CmmUtil.nvl(request.getParameter("p_num")); // 글번호(PK)

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);


        log.info("Pk키  : " + p_num);

        FoodDTO pDTO = new FoodDTO();

        pDTO.setP_num(p_num);

        marketService.FoodDelete(pDTO);


        FoodDTO fDTO = new FoodDTO();
        fDTO.setMarket_seq(market_seq); // 세션 가져와서 dto에 넣기

        List<FoodDTO> rList = marketService.FoodListZero(fDTO);

        if (rList == null) {
            rList = new ArrayList<>();
        }

        return rList;
    }







// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    @GetMapping(value = "MartMap") //회원정보에 등록된 마트위치 찾기
    public String MartMap(Model model) throws Exception{
        log.info(getClass().getName() + "MartMap start");

        List<MarketInfoDTO> mList = marketService.MartMap();

        if (mList == null){
            mList = new ArrayList<>();
            log.info("값이 반환되지 않음");
        }

        log.info(getClass().getName() + "MartMap end");

        model.addAttribute("mList",mList);

        return "/market/martMap";
    }


    @GetMapping(value = "PasingMap") // api 끌어와서 지도에 띄우기
    public String pasingMap(Model model) throws Exception{
        log.info(getClass().getName() + "pasingMap start");

        List<MarketPasingDTO> pList = marketService.pasingMap();

        if (pList == null){
            pList = new ArrayList<>();
            log.info("값이 반환되지 않음");
        }

        log.info(getClass().getName() + "pasingMap end");
        for(MarketPasingDTO mdto :pList){
           log.info(mdto.getBizplc_nm());
        }
        model.addAttribute("pList",pList);

        return "/market/PasingMap";
    }





    @PostMapping(value = "cart_add") // 상품 카트 추가
    public String cart_add(HttpServletRequest request,HttpSession session,ModelMap model ) throws Exception{
        log.info(getClass().getName() + "cart_add start");

        String url = ""; // 상황에 따라 url 이동
        String msg= ""; // 상황에 따라 alert창 띄우기
        CartDTO cDTO = null;

        try {
            String food_num = CmmUtil.nvl(request.getParameter("food_num"));
            String cart_count = CmmUtil.nvl(request.getParameter("cart_count"));
            String user_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));

            log.info("food_num" + food_num);
            log.info("cart_count" + cart_count);
            log.info("user_seq" + user_seq);


             cDTO = new CartDTO();

            cDTO.setUser_seq(Integer.parseInt(user_seq));
            cDTO.setCart_count(Integer.parseInt(cart_count));
            cDTO.setFood_num(Integer.parseInt(food_num));

            int res = marketService.InsertFoodInCart(cDTO);
            log.info("카트 결과 res" + res);

            if (res == 1){
                msg ="카트 담기에 성공하였습니다";
                url = "";


            }else if (res == 2){
                msg = "이미 담겨져 있는 품목입니다";
                url = "";
            }else {
                msg="오류로 인해서 카트에 담기에 실패하였습니다";
            }

        }catch (Exception e){ // 실패시 나오는 에러메세지
            msg=  "실패하였습니다. " + e;
            url= "/signup/SingupUser";
            log.info(e.toString());
            e.printStackTrace();

        }finally {
            log.info(getClass().getName() + "cart_add end");

            model.addAttribute("msg",msg);
            model.addAttribute("url",url);

            cDTO = null;


        }


        return "/redirect";
    }








}
