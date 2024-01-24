package blogApp.services;

import java.util.List;

import blogApp.payloads.CategoryDto;

public interface CategoryService {

	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto fetchCategory(int categoryId);
	
	List<CategoryDto> fetchAllCategory();
	
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	void deleteCategory(int categoryId);
}
