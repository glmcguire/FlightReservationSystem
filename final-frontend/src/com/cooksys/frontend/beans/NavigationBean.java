package com.cooksys.frontend.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class NavigationBean {

	public String home() {
		return "home";
	}

	public String login() {
		return "login";
	}

	public String register() {
		return "register";
	}

	public String profile() {
		return "profile";
	}
	
	public String update(){
		return "update";
	}
	
	public String book(){
		return "book";
	}

}
