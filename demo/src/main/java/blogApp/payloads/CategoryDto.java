package blogApp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto 
{
	private int categoryId;

	@NotBlank (message = "Title Cannot be blank")
	@Size(min = 4, message="Size must be of more than 4 chanrecters")
	private String title;
	
	@NotBlank (message = "Description Cannot be blank")
	private String description;
}
