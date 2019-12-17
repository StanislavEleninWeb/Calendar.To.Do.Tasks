package app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.User;

public interface UserDAO extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
}
