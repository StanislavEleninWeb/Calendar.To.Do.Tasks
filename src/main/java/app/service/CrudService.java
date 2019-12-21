package app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T, PK extends Serializable> {

	List<T> findAll();

	Optional<T> findById(PK id);

	void save(T t);

	void delete(T t);

	void deleteById(PK id);

}
