package kopo.poly.persistance.mapper;

import kopo.poly.dto.priceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPrcieMapper {

    void marketapide();

    int priceInSert(priceDTO mpDTO);

    List<priceDTO> getprice();
}
