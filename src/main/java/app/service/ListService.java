package app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.List;
import app.repository.ListRepository;

@Service
@Transactional
public class ListService implements CrudService<List, Long> {

	@Autowired
	private ListRepository listRepository;

	@Override
	public java.util.List<List> findAll() {
		return listRepository.findAll();
	}

	@Override
	public Optional<List> findById(Long id) {
		return listRepository.findById(id);
	}

	@Override
	public void save(List t) {
		listRepository.save(t);
	}

	@Override
	public void delete(List t) {
		listRepository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		listRepository.deleteById(id);
	}

}
