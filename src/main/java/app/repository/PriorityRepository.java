package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
