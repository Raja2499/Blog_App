package blogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogApp.payloads.ApiResponse;
import blogApp.payloads.CategoryDto;
import blogApp.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryServiece;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto newcategoryDto = this.categoryServiece.createCategory(categoryDto);
		return new ResponseEntity<>(newcategoryDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> fetchcategorys()
	{
		return ResponseEntity.ok(this.categoryServiece.fetchAllCategory());
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto,
												@PathVariable int categoryId)
	{
		CategoryDto updatedcategoryDto =  this.categoryServiece.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedcategoryDto);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deletecategory(@Valid @PathVariable int categoryId)
	{
		this.categoryServiece.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSinglecategory(@Valid @PathVariable int categoryId)
	{
		return ResponseEntity.ok(this.categoryServiece.fetchCategory(categoryId));
	}
}
