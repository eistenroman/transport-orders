package mx.ait.transportorders.order.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.order.dto.CreateOrderRequest;
import mx.ait.transportorders.order.dto.StatusOrderRequest;
import mx.ait.transportorders.order.exception.OrdersException;
import mx.ait.transportorders.order.exception.StatusOrdersException;
import mx.ait.transportorders.order.model.Orders;
import mx.ait.transportorders.order.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "order", description = "Endpoints para la gestión de ordenes")
public class OrderController {
    
	private Log logging = LogFactory.getLog(OrderController.class);
	
    private final OrderService orderService;
    
    @PostMapping(value = "/create")
    @Operation(summary = "Crear orden", description = "Crear una nueva orden")
    public ResponseEntity<Orders> create(@Valid @RequestBody CreateOrderRequest request){
    	
    	logging.info("Creando " + request);
    	
        return ResponseEntity.ok(orderService.createOrder(request));
    }
    
    @PutMapping(value = "/{id}")
    @Operation(summary = "Actualiza status de una orden", 
    		description = "Cambiar el estado de la orden, valida flujo válido entre status")
    public ResponseEntity<Orders> update(@PathVariable String id, 
    		@Valid @RequestBody StatusOrderRequest request) throws StatusOrdersException, OrdersException{
    		
    	logging.info("Actualizando orden id " + id + " a status " + request.getStatus());
    	
    	return ResponseEntity.ok(orderService.updateOrder(id, request));
    }
    
    @GetMapping(value = "/{id}")
    @Operation(summary = "Obtener orden por ID", description = "Consultar orden por ID")
    public ResponseEntity<Orders> get(@PathVariable String id) throws OrdersException{
    	
    	logging.info("Obtener orden id " + id);
    	
    	return ResponseEntity.ok(orderService.getOrder(id));
    }
    
    @GetMapping(value = "/{select}/{value}")
    @Operation(summary = "Obtener ordennes con filtros", 
    		description = "Listar órdenes con filtros: por status, fecha, origen o destino")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable String select, @PathVariable String value) 
    		throws OrdersException, StatusOrdersException{
    	
    	logging.info("getOrders ordens con " + select + " igual a " + value);
    	
    	return ResponseEntity.ok(orderService.getSelectOrders(select, value));
    }
    
}
