package kopo.poly.persistance.mapper;


import kopo.poly.dto.FoodDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMainMapper {


    void InsertFood(FoodDTO fDTO)throws Exception;

    List<FoodDTO> getFoodList();

    int update(FoodDTO fDTO)throws Exception;

    FoodDTO getFoodInfo(FoodDTO pDTO)throws Exception;
}
