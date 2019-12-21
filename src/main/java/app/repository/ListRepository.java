package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.List;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

}
