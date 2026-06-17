package mx.ait.transportorders.assignment.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.assignment.dto.AssignmentRequest;
import mx.ait.transportorders.assignment.dto.AssignmentResponse;
import mx.ait.transportorders.assignment.model.Assignment;
import mx.ait.transportorders.assignment.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {
	
	private final AssignmentRepository assignmentRepository;
	
	public AssignmentResponse createAssignment(AssignmentRequest assignmentRequest) throws IOException {
		
		Assignment assignment = Assignment.builder().idOrder(assignmentRequest.getIdOrder())
						.idDriver(assignmentRequest.getIdDriver())
						.file(assignmentRequest.getFile()!=null?assignmentRequest.getFile().getBytes():null)
						.image(assignmentRequest.getImage()!=null?assignmentRequest.getImage().getBytes():null)
						.build();
		
		assignment = assignmentRepository.save(assignment);
		
		AssignmentResponse response = AssignmentResponse.builder().id(assignment.getId())
				.idOrder(assignment.getIdOrder()).idDriver(assignment.getIdDriver()).build();
		
		return response;
				
	}
	
}
