package kopo.poly.service.impl;



import kopo.poly.paging.Criteria;
import kopo.poly.persistance.mapper.IPageMapper;
import kopo.poly.service.IPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("PageService")
@Slf4j
@RequiredArgsConstructor
public class PageService implements IPageService {
    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    private final IPageMapper pageMapper;


    public int noticeListCnt() throws Exception {
        return pageMapper.noticeListCnt();
    }

    public List<Map<String, Object>> noticeList(Criteria cri) throws Exception {
        return pageMapper.noticeList(cri);
    }

    @Override
    public int noticeSearchCnt(String keyword) throws Exception {
        log.info("테스트"+pageMapper.noticeSearchCnt(keyword));
        log.info("테스트"+keyword);
        return pageMapper.noticeSearchCnt(keyword);
    }

    @Override
    public List<Map<String, Object>> noticeSearchList(Criteria cri) throws Exception {
        return pageMapper.noticeSearchList(cri);
    }

    public int ShopListCnt() throws Exception {
        return pageMapper.ShopListCnt();
    }

    public List<Map<String, Object>> ShopList(Criteria cri) throws Exception {
        return pageMapper.ShopList(cri);
    }

    @Override
    public int ShopSearchCnt(String keyword) throws Exception {
        log.info("테스트"+pageMapper.noticeSearchCnt(keyword));
        log.info("테스트"+keyword);
        return pageMapper.ShopSearchCnt(keyword);
    }

    @Override
    public List<Map<String, Object>> ShopSearchList(Criteria cri) throws Exception {
        return pageMapper.ShopSearchList(cri);
    }

    @Override
    public int priceListCnt() throws Exception {
        return pageMapper.priceListCnt();
    }

    @Override
    public List<Map<String, Object>> priceList(Criteria cri) {
        return pageMapper.priceList(cri);
    }

    @Override
    public int priceSearchCnt(String keyword) {
        return pageMapper.priceSearchCnt(keyword);
    }

    @Override
    public List<Map<String, Object>> priceSearchList(Criteria cri) {
        return pageMapper.priceSearchList(cri);
    }

}
