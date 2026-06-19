package mx.ait.transportorders.driver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ait.transportorders.driver.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, java.util.UUID> {
	
    Optional<Driver> findByLicenseNumber(String licenseNumber);
    List<Driver> findByActive(boolean active);
    
}
