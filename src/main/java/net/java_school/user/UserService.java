package net.java_school.user;

import java.util.List;

public interface UserService {
	
	public int insert(User user);
	
	public User login(String email, String passwd);
	
	public int modify(User user);
	
	public void changePasswd(User user);
	
	public void bye(User user);

	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
	
	public List<User> getUsersByKeyword(String keyword);
	
}