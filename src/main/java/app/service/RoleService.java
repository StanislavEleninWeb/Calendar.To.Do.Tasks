package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Role;
import app.repository.RoleRepository;

@Service
@Transactional
public class RoleService implements CrudService<Role, Long> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public void save(Role t) {
		roleRepository.save(t);
	}

	@Override
	public void delete(Role t) {
		roleRepository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}

}
