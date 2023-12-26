package kopo.poly.persistance.mapper;

import kopo.poly.dto.kPayDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPayMapper {
    kPayDTO getPayKaKao();

    String getSumprice();

    void PayEnd();
}
