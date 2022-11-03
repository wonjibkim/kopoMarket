package kopo.poly.service.impl;


import kopo.poly.dto.FoodDTO;
import kopo.poly.persistance.mapper.IMainMapper;
import kopo.poly.service.IMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MainService")
@Slf4j
@RequiredArgsConstructor
public class MainService implements IMainService {

        private final IMainMapper mainMapper;



        @Transactional
        @Override
        public void InsertFood(FoodDTO fDTO) throws Exception {

                log.info(this.getClass().getName() + "InsertFood start!");
                log.info("name : "+fDTO.getP_name());
                mainMapper.InsertFood(fDTO);

                log.info(this.getClass().getName() + "InsertFood end");

        }

        @Override
        public List<FoodDTO> getFoodList() {

                List<FoodDTO>rList =mainMapper.getFoodList();

                return rList;
        }

        @Override
        public List<FoodDTO> foodsell_up() {

                List<FoodDTO>rList =mainMapper.getFoodList();

                return rList;
        }


}

