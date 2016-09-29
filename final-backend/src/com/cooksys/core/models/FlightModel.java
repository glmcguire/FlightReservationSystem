package com.cooksys.core.models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Long secondsTillNextDay;
	@XmlElement
	private Integer currentDay;
	@XmlElement
	private List<Flight> flights;
	
	@XmlTransient
	private Integer totalEta = 0;
	
	public FlightModel(Long secondsTillNextDay, Integer currentDay) {
		this.secondsTillNextDay = secondsTillNextDay;
		this.currentDay = currentDay;
	}
	public FlightModel() {
		// TODO Auto-generated constructor stub
	}
	public Long getSecondsTillNextDay() {
		return secondsTillNextDay;
	}
	public void setSecondsTillNextDay(Long secondsTillNextDay) {
		this.secondsTillNextDay = secondsTillNextDay;
	}
	public Integer getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(Integer currentDay) {
		this.currentDay = currentDay;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public Integer getTotalEta() {
		return totalEta;
	}
	public void setTotalEta(Integer getTotalEta) {
		this.totalEta = getTotalEta;
	}
}
