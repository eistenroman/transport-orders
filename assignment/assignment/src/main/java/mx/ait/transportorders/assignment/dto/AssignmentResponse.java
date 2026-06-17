package mx.ait.transportorders.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
	
	private java.util.UUID id;
	private java.util.UUID idOrder;
	private java.util.UUID idDriver;
	
}
