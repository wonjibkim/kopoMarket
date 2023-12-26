package kopo.poly.controller;

import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;
import kopo.poly.service.IMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "map")
public class MapController {

    @Resource(name = "MapService")
    private IMapService mapService;

    @GetMapping(value = "MartMap") //회원정보에 등록된 마트위치 찾기
    public String MartMap(Model model) throws Exception{
        log.info(getClass().getName() + "MartMap start");

        List<MarketInfoDTO> mList = mapService.MartMap();

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

        List<MarketPasingDTO> pList = mapService.pasingMap();

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


}
