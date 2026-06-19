package mx.ait.transportorders.assignment.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name="assignment", uniqueConstraints = {@UniqueConstraint(columnNames = {"idOrder"})})
public class Assignment {
	@Id
    @GeneratedValue
    @UuidGenerator
    private java.util.UUID id;
	@Column(nullable = false)
	private java.util.UUID idOrder;
	@Column(nullable = false)
	private java.util.UUID idDriver;
	@Lob
    @Column(name = "file", columnDefinition="LONGBLOB")
    private byte[] file;
	@Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
    private byte[] image;
	
}
