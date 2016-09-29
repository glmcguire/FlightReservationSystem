package com.cooksys.backend.beans.dao;

import java.util.List;

import com.cooksys.backend.model.User;



public interface UserDao {
	public User getUserByName(String username);
	public User getUserById(Integer id);
	public List<User> getUserByUsername(String username);
	public User registerUser(User user);
	public User updateUser(User user);
	public List<User> getUsers();
	public User getUserByEmail(String value);

}