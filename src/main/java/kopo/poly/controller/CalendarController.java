package kopo.poly.controller;

import kopo.poly.dto.CalendarDto;
import kopo.poly.dto.PayDTO;
import kopo.poly.service.ICalendarService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "Calendar")
public class CalendarController {

    @Resource(name = "CalendarService")
    private ICalendarService calendarService;

    @GetMapping(value = "sell_full") // 판매자측 일별 판매 목록
    public String InsertBoardPage(HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "InsertBoardPage start");

        String market_seq = CmmUtil.nvl((String) session.getAttribute("market_seq"));
        log.info("market_seq" + market_seq);

        PayDTO pDTO =  new PayDTO();
        pDTO.setMarket_seq(market_seq);

        List<PayDTO>rList = calendarService.getpaylist(pDTO);

        for (int i = 0; i <rList.size(); i++) {

            PayDTO mDTO = rList.get(i);
            log.info(mDTO.getP_name());
        }

        model.addAttribute("rList",rList);

        log.info(this.getClass().getName() + "InsertBoardPage end");

        return "/market/paysubmit";
    }

    @GetMapping(value = "pu_full") // 구매측 일별 판매 목록
    public String pu_full(HttpSession session, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "InsertBoardPage start");

        String user_seq = CmmUtil.nvl((String) session.getAttribute("seq"));
        log.info("user_seq" + user_seq);

        PayDTO pDTO =  new PayDTO();
        pDTO.setUser_seq(Integer.parseInt(user_seq));

        List<PayDTO>rList = calendarService.getpuList(pDTO);

        for (int i = 0; i <rList.size(); i++) {

            PayDTO mDTO = rList.get(i);
            log.info(mDTO.getP_name());
        }

        model.addAttribute("rList",rList);

        log.info(this.getClass().getName() + "InsertBoardPage end");

        return "/market/pr";
    }


}
