package kopo.poly.service;

import kopo.poly.dto.RecipeDTO;

import java.util.List;

public interface IRecipeService {

	// 웹상(CGV 홈페이지)에서 영화 순위정보 가져오기
	int collectRecipe() throws Exception;

	// 수집된 내용을 조회하기
	List<RecipeDTO> getRecipeName() throws Exception;

	List<RecipeDTO> getRecipe(String name) throws Exception;

	public List<RecipeDTO> getRecipeInfo(String name) throws Exception;

}

