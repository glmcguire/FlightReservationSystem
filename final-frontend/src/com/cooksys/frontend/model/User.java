package com.cooksys.frontend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Generated Jun 4, 2015 9:41:44 AM by Hibernate Tools 4.3.1

/**
 * User generated by hbm2java
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements java.io.Serializable {

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

	public User() {
	}

	public User(String username, String password, String email,
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
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	// /updates
	public void updateUsername(String username) {
		if(username == null){
			this.username = this.getUsername();
		}
		this.username = username;
	}

	public void updatePassword(String password) {
		if(password == null){
			this.password = this.getPassword();
		}
		this.password = password;
	}
	
	public void updateEmail(String email) {
		if(email == null){
			this.email = this.getEmail();
		}
		this.email = email;
	}
	
	public void updateFirstName(String firstName) {
		if(firstName == null){
			this.firstName = this.getFirstName();
		}
		this.firstName = firstName;
	}
	
	public void updateLastName(String lastName) {
		if(lastName == null){
			this.lastName = this.getLastName();
		}
		this.lastName = lastName;
	}
	
	public void updateStreet(String street) {
		if(street == null){
			this.street = this.getStreet();
		}
		this.street = street;
	}
	
	public void updateCity(String city) {
		if(city == null){
			this.city = this.getCity();
		}
		this.city = city;
	}
	
	public void updateState(String state) {
		if(state == null){
			this.state = this.getState();
		}
		this.state = state;
	}
	
	public void updateZip(String zip) {
		if(zip == null){
			this.zip = this.getZip();
		}
		this.zip = zip;
	}

	

}
