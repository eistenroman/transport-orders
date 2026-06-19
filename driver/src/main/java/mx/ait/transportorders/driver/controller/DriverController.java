package mx.ait.transportorders.driver.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.driver.dto.CreateDriverRequest;
import mx.ait.transportorders.driver.model.Driver;
import mx.ait.transportorders.driver.service.DriverService;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
@Tag(name = "driver", description = "Endpoints para la gestión de conductores")
public class DriverController {
    
	private Log logging = LogFactory.getLog(DriverController.class);
	
    private final DriverService driverService;
    
    @PostMapping(value = "/create")
    @Operation(summary = "Crear conductor", description = "Crear un conductor")
    public ResponseEntity<Driver> create(@Valid @RequestBody CreateDriverRequest request){
    	
    	logging.info("Creando " + request);
    	
        return ResponseEntity.ok(driverService.createDriver(request));
    }
    
    
    @GetMapping(value = "/drivers")
    @Operation(summary = "Obtener conductores", description = "Listar todos los conductores activos")
    public ResponseEntity<List<Driver>> getAll(){
    	
    	return ResponseEntity.ok(driverService.getActiveDrivers());
    }
    
}
