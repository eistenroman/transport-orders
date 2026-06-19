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
public class CreateOrderRequest {
	
	@NotBlank(message = "origin cannot be empty")
	@NotNull(message = "origin cannot be null")
	private String origin;
	
	@NotBlank(message = "destination cannot be empty")
	@NotNull(message = "destination cannot be null")
	private String destination;
}
