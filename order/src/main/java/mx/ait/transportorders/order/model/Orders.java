package mx.ait.transportorders.order.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Orders {
	@Id
    @GeneratedValue
    @UuidGenerator
    private java.util.UUID id;
	@Column(nullable = false)
	private Status status;
	@Column(nullable = false)
	private String origin;
	@Column(nullable = false)
	private String destination;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime updatedAt;
}
