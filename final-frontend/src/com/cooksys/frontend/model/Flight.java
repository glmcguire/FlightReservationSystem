package com.cooksys.frontend.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final AtomicInteger NEXTID = new AtomicInteger(1);
	
	@XmlElement
	private Integer flightId;
	
	@XmlElement
	private Location origin;
	@XmlElement
	private Location destination;
	
	@XmlElement
	private Integer departure;
	@XmlElement
	private Integer eta;
	@XmlElement
	private String flightStatus;
	
	

	@XmlTransient
	private Integer arrival;
	public Integer getArrival() {
		return departure + eta;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", origin=" + origin.getCity()
				+ ", destination=" + destination.getCity() + ", departure="
				+ departure + ", eta=" + eta + "]";
	}

	
	public static Integer getNextFlightID(){
		return NEXTID.incrementAndGet();
	}
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public Location getOrigin() {
		return origin;
	}
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	public Location getDestination() {
		return destination;
	}
	public void setDestination(Location destination) {
		this.destination = destination;
	}
	public Integer getDeparture() {
		return departure;
	}
	public void setDeparture(Integer departure) {
		this.departure = departure;
	}
	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}
}
