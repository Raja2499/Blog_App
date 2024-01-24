package blogApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import blogApp.entities.User;
import blogApp.exceptions.ResourceNotFoundException;
import blogApp.payloads.UserDto;
import blogApp.repositories.UserRepo;
import blogApp.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> fetchAllUser() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream()
				.map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto fetchUser(int userId) {
		return userToDto(userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId)));
	}

	@Override
	public void deleteUser(int userId) {
		this.userRepo.delete(this.userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User","id",userId)));
	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user = this.modelMapper.map(userDto,User.class);		
		return user;
	}

	private UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
}
