package blogApp.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogApp.entities.Category;
import blogApp.entities.Post;
import blogApp.entities.User;
import blogApp.exceptions.ResourceNotFoundException;
import blogApp.payloads.PostDto;
import blogApp.repositories.CategoryRepo;
import blogApp.repositories.PostRepo;
import blogApp.repositories.UserRepo;
import blogApp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper maodelMapper;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int categoryId, int userId) {
		
		User user = userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		
		Category category = categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", userId));
				
		Post post = dtoToPost(postDto);
		post.setImage("default.png");
		post.setCreatedAt(new Date());
		post.setCategory(category);
		post.setUser(user);
		return this.postToDto(post);
		
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post = postRepo.findById(postId).
				orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		postRepo.save(post);
		
		return this.postToDto(post);
	}

	@Override
	public List<PostDto> fetchAllPost() {
		
		List<Post> posts = postRepo.findAll();
		List<PostDto> postDtos = posts.stream().
				map(post->this.postToDto(post)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostDto fetchPost(int postId) {
		
		Post post = postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		return this.postToDto(post);
	}

	@Override
	public void deletePost(int postId) {
		
		Post post = postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> fetchPostByCategory(int categoryId) {
		
		Category category = categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("Category","categoryId" , categoryId));
		
		List<Post> posts = postRepo.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream().
				map(post->this.postToDto(post)).collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> fetchPostByUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		List<Post> posts = postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().
				map(post->this.postToDto(post)).collect(Collectors.toList());
		
		return postDtos;
	}
	
	private Post dtoToPost(PostDto postDto)
	{
		return this.maodelMapper.map(postDto, Post.class);
	}

	private PostDto postToDto(Post post)
	{
		return this.maodelMapper.map(post, PostDto.class);
	}


}
