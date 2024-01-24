package blogApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogApp.entities.Category;
import blogApp.exceptions.ResourceNotFoundException;
import blogApp.payloads.CategoryDto;
import blogApp.repositories.CategoryRepo;
import blogApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		categoryRepo.save(category);
		return categoryToDto(category);
	}

	@Override
	public CategoryDto fetchCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> fetchAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().
				map(category -> this.categoryToDto(category)).collect(Collectors.toList());
 		return categoryDtos;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		categoryRepo.save(category);
		return categoryToDto(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category","categoryId" , categoryId));
		categoryRepo.delete(category);
	}
	
	private Category dtoToCategory(CategoryDto categoryDto)
	{
		return modelMapper.map(categoryDto, Category.class);
	}

	private CategoryDto categoryToDto(Category category)
	{
		return modelMapper.map(category, CategoryDto.class);
	}

}
