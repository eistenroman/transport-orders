package mx.ait.transportorders.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.ait.transportorders.driver.dto.CreateDriverRequest;
import mx.ait.transportorders.driver.model.Driver;
import mx.ait.transportorders.driver.repository.DriverRepository;
import mx.ait.transportorders.driver.service.DriverService;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
	
	@Mock
    private DriverRepository driverRepository;
	
	@InjectMocks
    private DriverService driverService;
	
	@Test
    public void whenFindByGetActiveDrivers_thenReturnListDriver() {

        // Given
        List<Driver> listDriverMock = Arrays.asList(
        		new Driver(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),"Name","4563212", true));
        
        when(driverRepository.findByActive(true)).thenReturn(listDriverMock);

        // When
        List<Driver> found = driverService.getActiveDrivers();

        // Then
        assertEquals(listDriverMock, found);

    }
	
	@Test
    public void whenCreateDriver_thenReturnDriver() {

        // Given
		Driver driver = new Driver(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),"Name","4563212", true);
		
		when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        // When
		Driver createDriver = driverService.createDriver(new CreateDriverRequest("Juan Gomez", "3524532"));

        // Then
        assertEquals(driver, createDriver);

    }
}
