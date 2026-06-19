package mx.ait.transportorders.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
	
	private java.util.UUID id;
	private String name;
	private String licenseNumber;
	boolean active;
	
}
