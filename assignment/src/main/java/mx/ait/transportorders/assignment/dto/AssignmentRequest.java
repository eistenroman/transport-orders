package mx.ait.transportorders.assignment.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
	
	private java.util.UUID idOrder;
	private java.util.UUID idDriver;
	private MultipartFile file;
	private MultipartFile image;
	
}
