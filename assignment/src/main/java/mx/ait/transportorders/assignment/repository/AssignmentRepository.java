package mx.ait.transportorders.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ait.transportorders.assignment.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, java.util.UUID> {
	
}
