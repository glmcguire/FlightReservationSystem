package com.cooksys.frontend.beans;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.model.User;

@Component
@Scope("request")
public class LoginBean {
	
	private URL url;
	private Logger log = LoggerFactory.getLogger(LoginBean.class);
	private User user;
	private RestTemplate restTemplate;

	@Autowired
	private AuthenticationBean authentication;

	private Boolean loginFailed = true; // may need to change default

	@PostConstruct
	public void init() {
		user = new User();
	}

	public String login() {
		if (authentication.login(user)) {
			loginFailed = false;
			return "login-success";
		} else {
			loginFailed = true;
			return "login-failure";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoginFailed() {
		return loginFailed;
	}

	public void setLoginFailed(Boolean loginFailed) {
		this.loginFailed = loginFailed;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void login(){
//		try{
//			//restTemplate.postForObject("http://localhost:8080/final-backend/login", user, User.class);
//			
//			
//			url = new URL("http://localhost:8080/final-backend/login");
//			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
//			httpsURLConnection.setDoOutput(true);
//			httpsURLConnection.setRequestMethod("POST");
//			httpsURLConnection.addRequestProperty("Content-Type", "application/xml");
//			
//			JAXBContext ctx = JAXBContext.newInstance(User.class);
//			Marshaller m = ctx.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			OutputStream os = httpsURLConnection.getOutputStream();
//			
//			m.marshal(user, os);
//			m.marshal(user, System.out);
//			httpsURLConnection.getResponseCode();
//			os.flush();
//
//			httpsURLConnection.disconnect();
//		
//		} catch (IOException | JAXBException e){
//			e.printStackTrace();
//		}
//	}
//	
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
	
}
