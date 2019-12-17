package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Task;

public interface TaskDAO extends JpaRepository<Task, Long>{

}
