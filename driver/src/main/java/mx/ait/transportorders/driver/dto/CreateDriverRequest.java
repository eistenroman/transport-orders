package mx.ait.transportorders.driver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDriverRequest {
	
	@NotBlank(message = "name cannot be empty")
	@NotNull(message = "name cannot be null")
	private String name;
	@NotBlank(message = "licenseNumber cannot be empty")
	@NotNull(message = "licenseNumber cannot be null")
	private String licenseNumber;
}
