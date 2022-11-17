package kopo.poly.service;


import kopo.poly.dto.FoodDTO;

import java.util.List;

public interface IMainService {

    void InsertFood(FoodDTO fDTO) throws Exception;

    List<FoodDTO> getFoodList();

    int update(FoodDTO fDTO)throws Exception;


    FoodDTO getFoodInfo(FoodDTO pDTO) throws Exception;
}
