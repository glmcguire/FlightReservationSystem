package com.cooksys.frontend.beans;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.frontend.beans.dao.UserDao;
import com.cooksys.frontend.beans.dao.impl.PasswordEncoder;
import com.cooksys.frontend.model.UpdatedUser;
import com.cooksys.frontend.model.User;

@Component
@Scope("request")
public class RegistrationBean {

	private Logger log = LoggerFactory.getLogger(RegistrationBean.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthenticationBean auth;

	private User user;
	private User updatedUser;

	@PostConstruct
	public void init() {
		user = new User();

	}

	public String register() {

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

	public String update() {

		User dbUser = userDao.updateUser(auth.getUser());

		if (dbUser != null) {
			log.info("update-success");
			return "update-success";
		} else {
			log.info("update-failure");
			return "update-failure";
		}
	}

	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	public AuthenticationBean getAuth() {
		return auth;
	}

	public void setAuth(AuthenticationBean auth) {
		this.auth = auth;
	}

}
