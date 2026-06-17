package mx.ait.transportorders.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.ait.transportorders.order.dto.CreateOrderRequest;
import mx.ait.transportorders.order.exception.OrdersException;
import mx.ait.transportorders.order.exception.StatusOrdersException;
import mx.ait.transportorders.order.model.Orders;
import mx.ait.transportorders.order.model.Status;
import mx.ait.transportorders.order.repository.OrderRepository;
import mx.ait.transportorders.order.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
    private OrderRepository orderRepository;
	
	@InjectMocks
    private OrderService orderService;
	
	@Test
    public void whenGetOrder_thenReturnOrders() throws OrdersException {

        // Given
        Orders mockOrder = new Orders();
        mockOrder.setId(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"));
        mockOrder.setStatus(Status.DELIVERED);
        mockOrder.setDestination("Destino");
        mockOrder.setOrigin("Origen");
        mockOrder.setCreatedAt(LocalDateTime.now());
        
        when(orderRepository.findById(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"))).thenReturn(Optional.of(mockOrder));

        // When
        Orders found = orderService.getOrder("bbd94054-81eb-4d78-ac8c-fad4f04c13a9");
        
        // Then
        assertEquals(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"), found.getId());

    }
	
	@Test
    public void whenGetSelectOrders_thenReturnListOrders() throws OrdersException, StatusOrdersException {

        // Given
        List<Orders> mockOrderList = Arrays.asList(
        		new Orders(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"),
        				Status.DELIVERED,"Origen","Destino",LocalDateTime.now(),null));
        
        when(orderRepository.findByOrigin(anyString())).thenReturn(mockOrderList);

        // When
        List<Orders> found = orderService.getSelectOrders("origen", "Origen");
        
        // Then
        assertEquals(mockOrderList, found);

    }
	
	@Test
    public void whenCreateOrders_thenReturnOrders() {

        // Given
		Orders orders = new Orders(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"),
				Status.DELIVERED,"Origen","Destino",LocalDateTime.now(),null);
		
		when(orderRepository.save(any(Orders.class))).thenReturn(orders);

        // When
		Orders createOrders = orderService.createOrder(new CreateOrderRequest("Origen A1", "Destino D1"));

        // Then
        assertEquals(orders, createOrders);

    }
	
	@Test
    public void whenUpdateOrders_thenReturnOrders() throws StatusOrdersException, OrdersException {
		
    }
}
