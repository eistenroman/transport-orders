package mx.ait.transportorders.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.ait.transportorders.assignment.dto.AssignmentRequest;
import mx.ait.transportorders.assignment.dto.AssignmentResponse;
import mx.ait.transportorders.assignment.model.Assignment;
import mx.ait.transportorders.assignment.repository.AssignmentRepository;
import mx.ait.transportorders.assignment.service.AssignmentService;

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {
	
	@Mock
    private AssignmentRepository assignmentRepository;
	
	@InjectMocks
    private AssignmentService assignmentService;
	
	@Test
    public void whenCreateAssignment_thenReturnAssignment() throws IOException {

        // Given
		Assignment assignment = new Assignment();
		assignment.setIdDriver(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"));
		assignment.setIdOrder(UUID.fromString("6a906da5-8f51-45b7-9af9-4b5fa10a8265"));
		
		when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment);

        // When
		AssignmentResponse createAssignment = assignmentService.createAssignment(new AssignmentRequest(
				UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"), 
				UUID.fromString("6a906da5-8f51-45b7-9af9-4b5fa10a8265"), null, null));

        // Then
        assertEquals(assignment.getIdOrder(), createAssignment.getIdOrder());

    }
}
