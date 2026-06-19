package mx.ait.transportorders.order.dto;

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
public class StatusOrderRequest {
	
	@NotBlank(message = "status cannot be empty")
	@NotNull(message = "status cannot be null")
	private String status;
}
