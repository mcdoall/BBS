package net.java_school.user;

import java.util.List;

import net.java_school.mybatis.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
		
	public int insert(User user) {
		return userMapper.insert(user);
	}
	
	public User login(String email, String passwd) {
		return userMapper.login(email, passwd);
	}
	
	public int modify(User user) {
		return userMapper.modify(user);
	}
	
	public void changePasswd(User user) {
		userMapper.changePasswd(user);
	}
	
	public void bye(User user) {
		userMapper.bye(user);
	}

	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}
	
	public List<User> getUsersByKeyword(String keyword) {
		return userMapper.getUsersByKeyword(keyword);
	}
	
}