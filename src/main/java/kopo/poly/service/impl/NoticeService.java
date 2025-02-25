package kopo.poly.service.impl;


import kopo.poly.dto.NoticeDTO;
import kopo.poly.persistance.mapper.IMainMapper;
import kopo.poly.persistance.mapper.INoticeMapper;
import kopo.poly.service.IMainService;
import kopo.poly.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("NoticeService")
@Slf4j
@RequiredArgsConstructor
public class NoticeService implements INoticeService {

        // RequiredArgsConstructor 어노테이션으로 생성자를 자동 생성함
        // noticeMapper 변수에 이미 메모리에 올라간 Mapper 객체를 넣어줌
        // 예전에는 autowired 어노테이션를 통해 설정했었지만, 이젠 생성자를 통해 객체 주입함
        private final INoticeMapper noticeMapper;

        @Override
        public List<NoticeDTO> getNoticeList() throws Exception {

                log.info(this.getClass().getName() + ".getNoticeList start!");

                return noticeMapper.getNoticeList();

        }

        @Transactional
        @Override
        public void InsertNoticeInfo(NoticeDTO pDTO) throws Exception {

                log.info(this.getClass().getName() + ".InsertNoticeInfo start!");

                noticeMapper.InsertNoticeInfo(pDTO);
        }

        @Transactional
        @Override
        public NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception {

                log.info(this.getClass().getName() + ".getNoticeInfo start!");

                return noticeMapper.getNoticeInfo(pDTO);

        }

        @Override
        public void noticeDelete(String nSeq) throws Exception {
                noticeMapper.noticeDelete(nSeq);
        }

        @Override
        public void noticeUpdate(NoticeDTO nDTO) throws Exception {
                noticeMapper.noticeUpdate(nDTO);
        }
}

