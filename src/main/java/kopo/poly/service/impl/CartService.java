package kopo.poly.service.impl;


import kopo.poly.dto.CartDTO;
import kopo.poly.persistance.mapper.ICartMapper;
import kopo.poly.service.ICartService;
import kopo.poly.service.IMapService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("CartService")
public class CartService implements ICartService {

    private final ICartMapper cartMapper;

    @Override
    public int InsertFoodInCart(CartDTO cDTO) throws Exception {

        int res = 0;

        if(cDTO == null){
            cDTO = new CartDTO();
            log.info("cDTO가 널이어서 메모리에 강제로 올림");
        } //널처리

        CartDTO rDTO = cartMapper.SelectCountInCart(cDTO); // 중복체크

        if (rDTO == null){
            rDTO = new CartDTO();
        }

        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            res = 2;
        }else {
            log.info("user_seq : "+cDTO.getUser_seq());
            int success = cartMapper.InsertFoodInCart(cDTO);

            if(success > 0){
                res =1;
            }else {
                res = 0;
            }
        }


        return res;
    }
}
