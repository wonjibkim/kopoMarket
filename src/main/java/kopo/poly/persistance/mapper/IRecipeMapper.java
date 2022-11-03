package kopo.poly.persistance.mapper;

import kopo.poly.dto.RecipeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRecipeMapper {

	// 수집된 내용 DB에 등록
	int InsertRecipeInfo(RecipeDTO pDTO) throws Exception;

	// 수집된 내용을 조회하기
	List<RecipeDTO> getRecipeInfo(RecipeDTO pDTO) throws Exception;
}

