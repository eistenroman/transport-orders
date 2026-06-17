package mx.ait.transportorders.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.order.dto.CreateOrderRequest;
import mx.ait.transportorders.order.dto.StatusOrderRequest;
import mx.ait.transportorders.order.exception.OrdersException;
import mx.ait.transportorders.order.exception.StatusOrdersException;
import mx.ait.transportorders.order.model.Orders;
import mx.ait.transportorders.order.model.Status;
import mx.ait.transportorders.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private Log logging = LogFactory.getLog(OrderService.class);
	
	private final OrderRepository orderRepository;
	
	public Orders createOrder(CreateOrderRequest orderRequest) {
		
		Orders order = Orders.builder().status(Status.CREATED)
						.origin(orderRequest.getOrigin())
						.destination(orderRequest.getDestination())
						.createdAt(LocalDateTime.now()).build();
		
		order = orderRepository.save(order);
		logging.info("Creada " + order);
		
		return order;
				
	}
	
	public Orders updateOrder(String id, StatusOrderRequest orderRequest) throws StatusOrdersException, OrdersException {
		
		Orders order = getOrder(id);
		
		validateStatus(order.getStatus(), obtenStatus(orderRequest.getStatus()));
		order.setStatus(obtenStatus(orderRequest.getStatus()));
		order.setUpdatedAt(LocalDateTime.now());
		
		order = orderRepository.save(order);
		logging.info("Actualizada " + order);
		
		return order;
	}
	
	private void validateStatus(Status status, Status status2) throws StatusOrdersException {
		
		if(status2.equals(Status.CREATED)) {
			throw new StatusOrdersException("La orden en estatus " + status2.toString() 
			+ " no puede actualizar a CREATED");
		}
		if(status2.equals(Status.CANCELLED) && !status.equals(Status.CREATED)) {
			throw new StatusOrdersException("Una orden en estatus " + status2.toString() 
			+ " no puede actualizar a CANCELLED");
		}
		if(status2.equals(Status.IN_TRANSIT) && !status.equals(Status.CREATED)){
			throw new StatusOrdersException("Una orden en estatus " + status2.toString() 
			+ "no puede actualizar a IN_TRANSIT");
		}
		if(status2.equals(Status.DELIVERED) && !status.equals(Status.IN_TRANSIT)) {
			throw new StatusOrdersException("Una orden en estatus " + status2.toString() 
									+ " no puede actualizar a DELIVERED" );
		}
	}

	public Orders getOrder(String id) throws OrdersException {
		Optional<Orders> optionalOrder = orderRepository.findById(java.util.UUID.fromString(id));
		
		if(!optionalOrder.isEmpty()) {
			return optionalOrder.get();
		}
		
		throw new OrdersException("La orden con id " + id + "no fue encontrada");
	}
	
	public List<Orders> getSelectOrders(String select, String value) throws OrdersException, StatusOrdersException {
		
		switch(select) {
			case "status":
				return orderRepository.findByStatus(obtenStatus(value));
			case "origen":
				return orderRepository.findByOrigin(value);
			case "destino":
				return orderRepository.findByDestination(value);
			case "fecha":
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate fecha = LocalDate.parse(value, formato);
				return orderRepository.findByFechaOnly(fecha);
			default:
				throw new OrdersException("El criterio no es reconocido");		
		}
	}

	private Status obtenStatus(String value) throws StatusOrdersException {
		
		switch(value) {
			case "CREATED":
				return Status.CREATED;
			case "IN_TRANSIT":
				return Status.IN_TRANSIT;
			case "DELIVERED":
				return Status.DELIVERED;
			case "CANCELLED":
				return Status.CANCELLED;
			default:
				throw new StatusOrdersException("El status no es reconocido");	
		}
	}
	
}
