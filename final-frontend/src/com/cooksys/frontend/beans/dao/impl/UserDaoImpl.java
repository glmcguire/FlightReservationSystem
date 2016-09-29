package com.cooksys.frontend.beans.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.beans.dao.UserDao;
import com.cooksys.frontend.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	private RestTemplate restTemplate = new RestTemplate();
	private Logger log = LoggerFactory.getLogger(UserDaoImpl.class);


	@Override
	public User getUserByName(String name) {
		return restTemplate
				.getForObject("http://localhost:8080/final-backend/users/"
						+ name, User.class);
	}

	@Override
	public User registerUser(User user) {
		return restTemplate.postForObject(
				"http://localhost:8080/final-backend/register", user,
				User.class);
	}

	@Override
	public User getUserByEmail(String email) {
		return restTemplate.getForObject(
				"http://localhost:8080/final-backend/userEmail/" + email,
				User.class);
	}
	
	@Override 
	public User updateUser(User user) {
		return restTemplate.postForObject(
				"http://localhost:8080/final-backend/update", user,
				User.class);
	}

}
