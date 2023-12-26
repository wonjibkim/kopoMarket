package kopo.poly.persistance.mapper;

import kopo.poly.dto.MarketInfoDTO;
import kopo.poly.dto.MarketPasingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMapMapper {

    //    void maketInsertInfo(List<MarketPasingDTO> mpList);
    void maketInsertInfo(MarketPasingDTO mpDTO); //api 값 db에 넣기
    void marketapide(); // api 가져오기전에 삭제하기

    List<MarketInfoDTO> MartMap();

    List<MarketPasingDTO>pasingMap();
}
