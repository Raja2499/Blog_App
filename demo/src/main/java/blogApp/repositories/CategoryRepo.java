package blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogApp.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	

}
