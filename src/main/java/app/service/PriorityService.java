package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Priority;
import app.repository.PriorityRepository;

@Service
@Transactional
public class PriorityService implements CrudService<Priority, Long> {

	@Autowired
	private PriorityRepository priorityRepository;

	@Override
	public List<Priority> findAll() {
		return priorityRepository.findAll();
	}

	@Override
	public Optional<Priority> findById(Long id) {
		return priorityRepository.findById(id);
	}

	@Override
	public void save(Priority t) {
		priorityRepository.save(t);
	}

	@Override
	public void delete(Priority t) {
		priorityRepository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		priorityRepository.deleteById(id);
	}

}
