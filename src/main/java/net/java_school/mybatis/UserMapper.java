package net.java_school.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.java_school.user.User;

public interface UserMapper {
	
	public int insert(User user);
	
	public User login(@Param("email") String email, @Param("passwd") String passwd);
	
	public int modify(User user);
	
	public void changePasswd(User user);

	public void bye(User user);

	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
	
	public List<User> getUsersByKeyword(String keyword);

	
}
