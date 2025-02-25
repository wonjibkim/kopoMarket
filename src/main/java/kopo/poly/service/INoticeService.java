package kopo.poly.service;


import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface INoticeService {

    List<NoticeDTO> getNoticeList() throws Exception;

    void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;

    NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

    void noticeDelete(String nSeq) throws Exception;

    void noticeUpdate(NoticeDTO nDTO) throws Exception;



}
