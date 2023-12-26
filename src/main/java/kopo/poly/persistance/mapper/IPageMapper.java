package kopo.poly.persistance.mapper;
import kopo.poly.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IPageMapper {
    List<Map<String, Object>> noticeList(Criteria cri) throws Exception;

    int noticeListCnt() throws Exception;


    int noticeSearchCnt(String keyword)throws Exception;

    List<Map<String, Object>> noticeSearchList(Criteria cri) throws Exception;

    List<Map<String, Object>> ShopList(Criteria cri) throws Exception;

    int ShopListCnt() throws Exception;


    int ShopSearchCnt(String keyword)throws Exception;

    List<Map<String, Object>> ShopSearchList(Criteria cri) throws Exception;

    int priceListCnt();

    List<Map<String, Object>> priceList(Criteria cri);

    int priceSearchCnt(String keyword);

    List<Map<String, Object>> priceSearchList(Criteria cri);
}
