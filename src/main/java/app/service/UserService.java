package app.service;

import java.util.List;

import app.entity.User;

public interface UserService {

	public void register(User user);
	
	public void save(User user);
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
}
