package kopo.poly.service.impl;

import kopo.poly.dto.CalendarDto;
import kopo.poly.dto.PayDTO;
import kopo.poly.dto.priceDTO;
import kopo.poly.persistance.mapper.ICalendarMapper;
import kopo.poly.service.ICalendarService;
import kopo.poly.util.XmlPasingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service(value = "CalendarService")
public class CalendarService implements ICalendarService {

    private final ICalendarMapper calendarMapper;


    @Override
    public List<PayDTO> getpaylist(PayDTO pDTO) {

        List<PayDTO>rList = calendarMapper.getpaylist(pDTO);

        return rList;
    }

    @Override
    public List<PayDTO> getpuList(PayDTO pDTO) {

        List<PayDTO>rList = calendarMapper.getpuList(pDTO);

        return rList;
    }
}
