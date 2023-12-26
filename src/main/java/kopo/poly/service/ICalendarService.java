package kopo.poly.service;


import kopo.poly.dto.CalendarDto;
import kopo.poly.dto.PayDTO;

import java.util.List;

public interface ICalendarService {
//    List<CalendarDto> getCalendarList(String userId);


    List<PayDTO> getpaylist(PayDTO pDTO);

    List<PayDTO> getpuList(PayDTO pDTO);
}
