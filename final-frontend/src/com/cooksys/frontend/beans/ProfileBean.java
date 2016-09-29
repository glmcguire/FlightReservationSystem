package com.cooksys.frontend.beans;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.frontend.beans.dao.UserDao;
import com.cooksys.frontend.model.User;

@Component
@Scope("request")
public class ProfileBean {
	
	private Logger log = LoggerFactory.getLogger(ProfileBean.class);
	
	@Autowired
	private AuthenticationBean auth;
	@Autowired
	private UserDao userDao;

	
	
	private User user;
	
	@PostConstruct
	public void init(){
		if(auth.isLoggedIn()){
			log.info("auth.isLoggedIn() = true");
			user = auth.getUser();
			
			
			//add flights here later...
			
		} else {
			log.info("auth.isLoggedIn() = false");
			user = new User();
		
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
}
