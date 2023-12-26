package kopo.poly.persistance.mapper;


import kopo.poly.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface INoticeMapper {

   List<NoticeDTO> getNoticeList() throws Exception;

   void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;

   NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

   void noticeDelete(String nSeq) throws Exception;

   void noticeUpdate(NoticeDTO nDTO) throws Exception;


}
