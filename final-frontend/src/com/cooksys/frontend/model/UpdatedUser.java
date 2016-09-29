package com.cooksys.frontend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdatedUser {
	
	@XmlElement
	private Integer id;
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	@XmlElement
	private String email;
	@XmlElement
	private String firstName;
	@XmlElement
	private String lastName;
	@XmlElement
	private String street;
	@XmlElement
	private String city;
	@XmlElement
	private String state;
	@XmlElement
	private String zip;
	
	
	@XmlTransient
	private User user;

	public UpdatedUser() {
	}

	public UpdatedUser(String username, String password, String email,
			String firstName, String lastName, String street, String city,
			String state, String zip) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = user.getId();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		if(username == null){
			this.username = user.getUsername();
		}
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		if(password == null){
			this.password = user.getPassword();
		}
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		if(email == null){
			this.email = user.getEmail();
		}
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName == null){
			this.firstName = user.getFirstName();
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		if(lastName == null){
			this.lastName = user.getLastName();
		}
		this.lastName = lastName;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		if(street == null){
			this.street = user.getStreet();
		}
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		if(city == null){
			this.city = user.getCity();
		}
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		if(state == null){
			this.state = user.getState();
		}
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		if(zip == null){
			this.zip = user.getZip();
		}
		this.zip = zip;
	}

}
