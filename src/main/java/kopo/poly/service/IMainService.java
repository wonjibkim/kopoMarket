package kopo.poly.service;


import kopo.poly.dto.FoodDTO;

import java.util.List;

public interface IMainService {

    void InsertFood(FoodDTO fDTO) throws Exception;

    List<FoodDTO> getFoodList();
    List<FoodDTO> foodsell_up();




}
