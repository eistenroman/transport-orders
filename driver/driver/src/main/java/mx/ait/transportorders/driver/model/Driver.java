package mx.ait.transportorders.driver.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="driver", uniqueConstraints = {@UniqueConstraint(columnNames = {"licenseNumber"})})
public class Driver {
	@Id
    @GeneratedValue
    @UuidGenerator // Genera el UUID automáticamente (en Hibernate)
    private java.util.UUID id;
	@Basic
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String licenseNumber;
	boolean active;

}
