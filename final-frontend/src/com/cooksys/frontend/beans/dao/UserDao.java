package com.cooksys.frontend.beans.dao;

import com.cooksys.frontend.model.User;

public interface UserDao {
	public User getUserByName(String name);
	public User registerUser(User user);
	public User getUserByEmail(String value);
	public User updateUser(User user);
	
}
