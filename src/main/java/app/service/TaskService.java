package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Task;
import app.repository.TaskRepository;

@Service
@Transactional
public class TaskService implements CrudService<Task, Long> {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public void save(Task t) {
		taskRepository.save(t);
	}

	@Override
	public void delete(Task t) {
		taskRepository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}

}
