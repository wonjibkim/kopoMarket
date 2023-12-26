package kopo.poly.service;


import kopo.poly.paging.Criteria;

import java.util.List;
import java.util.Map;

public interface IPageService {
    int noticeListCnt() throws Exception;
    List<Map<String, Object>> noticeList(Criteria cri) throws Exception;

    int noticeSearchCnt(String keyword)throws Exception;

    List<Map<String, Object>> noticeSearchList(Criteria cri) throws Exception;

    int ShopListCnt() throws Exception;
    List<Map<String, Object>> ShopList(Criteria cri) throws Exception;

    int ShopSearchCnt(String keyword)throws Exception;

    List<Map<String, Object>> ShopSearchList(Criteria cri) throws Exception;

    int priceListCnt()throws Exception;

    List<Map<String, Object>> priceList(Criteria cri);

    int priceSearchCnt(String keyword);

    List<Map<String, Object>> priceSearchList(Criteria cri);
}
