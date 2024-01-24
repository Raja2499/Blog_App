package blogApp.services;

import java.util.List;

import blogApp.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, int userId);
	
	List<UserDto> fetchAllUser();
	
	UserDto fetchUser(int userId);
	
	void deleteUser(int userId);
	
	
	
}
