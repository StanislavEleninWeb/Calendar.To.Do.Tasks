package app.service;

import app.entity.User;

public interface UserService {

	public void save(User user);
	
	public User findByUsername(String username);
	
}
