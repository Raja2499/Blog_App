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
import blogApp.payloads.PostDto;
import blogApp.services.PostService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	@Autowired
	private PostService postServiece;
	
	@PostMapping("/users/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, 
												@PathVariable int userId, 
												@PathVariable int categoryId)
	{
		PostDto newPostDto = this.postServiece.createPost(postDto, categoryId, userId);
		return new ResponseEntity<>(newPostDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> fetchPosts()
	{
		return ResponseEntity.ok(this.postServiece.fetchAllPost());
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
												@PathVariable int postId)
	{
		PostDto updatedPostDto =  this.postServiece.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPostDto);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@Valid @PathVariable int postId)
	{
		this.postServiece.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@Valid @PathVariable int postId)
	{
		return ResponseEntity.ok(this.postServiece.fetchPost(postId));
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostFromUser(@Valid @PathVariable int userId)
	{
		return ResponseEntity.ok(postServiece.fetchPostByUser(userId));
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@Valid @PathVariable int categoryId)
	{
		return ResponseEntity.ok(postServiece.fetchPostByUser(categoryId));
	}
	
}
