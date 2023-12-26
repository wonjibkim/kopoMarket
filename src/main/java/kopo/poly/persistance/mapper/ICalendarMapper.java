package kopo.poly.persistance.mapper;

import kopo.poly.dto.CalendarDto;
import kopo.poly.dto.PayDTO;
import kopo.poly.dto.priceDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface ICalendarMapper {
//    List<CalendarDto> getCalendarList(CalendarDto calendarDto);

    List<PayDTO> getpaylist(PayDTO pDTO);

    List<PayDTO> getpuList(PayDTO pDTO);
}
