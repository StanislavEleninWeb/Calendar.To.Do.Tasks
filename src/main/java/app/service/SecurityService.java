package app.service;

public interface SecurityService {

	public String findloggedInUser();
	
	public void autoLogin(String username, String password);
	
}
