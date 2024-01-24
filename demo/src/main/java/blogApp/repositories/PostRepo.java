package blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogApp.entities.Category;
import blogApp.entities.Post;
import blogApp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user); 
	
	List<Post> findByCategory(Category category);

}
