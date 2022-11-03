package kopo.poly.persistance.mapper;


import kopo.poly.dto.MarketInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMarketInfoMapper {

    int insertMarketInfo(MarketInfoDTO mDTO) throws Exception;

    MarketInfoDTO getMarketExists(MarketInfoDTO mDTO) throws Exception;

}
