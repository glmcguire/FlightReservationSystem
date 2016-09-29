package com.cooksys.backend.beans;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.backend.beans.dao.UserDao;
import com.cooksys.backend.beans.dao.impl.PasswordEncoder;
import com.cooksys.backend.model.User;

@Component
@Scope("request")
public class RegistrationBean {

	private Logger log = LoggerFactory.getLogger(RegistrationBean.class);
	private PasswordEncoder encoder = new PasswordEncoder();

	@Autowired
	private UserDao userDao;

	private User user;

	@PostConstruct
	public void init() {
		user = new User();

	}

	public String register() {
		
		String encodedPassword = encoder.encryptPassword(user.getPassword());
		user.setPassword(encodedPassword);
		
		User dbUser = userDao.registerUser(user);

		if (dbUser != null) {
			log.info("registration-success");
			return "registration-success";
		} else {
			log.info("registration-failure");
			return "registration-failure";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
