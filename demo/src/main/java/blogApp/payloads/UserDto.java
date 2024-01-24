package blogApp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

	private int userId;
	
	@NotEmpty (message = "Name cannot be empty")
	private String name;
	
	@Email (message = "Enter a valid email")
	@NotEmpty (message = "Email cannot be empty")
	private String email;
	
	@NotEmpty (message = "password cannot be empty")
	private String password;
	private String about;
}
