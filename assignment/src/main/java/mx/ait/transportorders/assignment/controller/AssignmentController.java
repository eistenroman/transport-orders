package mx.ait.transportorders.assignment.controller;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.assignment.dto.AssignmentRequest;
import mx.ait.transportorders.assignment.dto.AssignmentResponse;
import mx.ait.transportorders.assignment.service.AssignmentService;

@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
@Tag(name = "assignment", description = "Endpoints para la gestión de asignaciones")
public class AssignmentController {
    
	private Log logging = LogFactory.getLog(AssignmentController.class);
	
    private final AssignmentService assignmentService;
    
    @PostMapping(value = "/create")
    @Operation(summary = "Crear asignacion", description = "Asignar un conductor a una orden")
    public ResponseEntity<AssignmentResponse> create(@RequestParam UUID idOrder, @RequestParam UUID idDriver,
    		@RequestParam MultipartFile file, @RequestParam MultipartFile image) throws IOException{
    	
    	logging.info("Asignar orden " + idOrder + " al condutor " + idDriver);
    	
    	AssignmentRequest request = AssignmentRequest.builder().idDriver(idDriver).idOrder(idOrder)
    							.file(file).image(image).build();
    	
        return ResponseEntity.ok(assignmentService.createAssignment(request));
    }
    
}
