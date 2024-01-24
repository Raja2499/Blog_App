package blogApp.services;

import java.util.List;

import blogApp.payloads.PostDto;


public interface PostService {


	PostDto createPost(PostDto postDto, int categoryId, int userId);
	
	PostDto updatePost(PostDto postDto, int postId);
	
	List<PostDto> fetchAllPost();
	
	PostDto fetchPost(int postId);
	
	void deletePost(int postId);
	
	List<PostDto> fetchPostByCategory(int categoryId);
	
	List<PostDto> fetchPostByUser(int userId);
}
