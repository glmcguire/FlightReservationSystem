package com.cooksys.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.core.models.Flight;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Trip {
	
	private Flight flight;
//	private List<Flight> flights = new ArrayList<Flight>();
	private Boolean tripComplete = false;
	private List<Trip> trips = new ArrayList<Trip>();
	private Trip parent = null;
	private String flightId;

	public Trip() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Trip [flightId=" + flightId + ", tripComplete=" + tripComplete
				+ "]";
	}

	public Trip(Trip parent, Flight flight, String flightId) {
		this.parent = parent;
		this.flight = flight;
		this.flightId = flightId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

//	public List<Flight> getFlights() {
//		return flights;
//	}
//
//	public void setFlights(List<Flight> flights) {
//		this.flights = flights;
//	}


	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Boolean getTripComplete() {
		return tripComplete;
	}

	public void setTripComplete(Boolean tripComplete) {
		this.tripComplete = tripComplete;
	}

	public Trip getParent() {
		return parent;
	}

	public void setParent(Trip parent) {
		this.parent = parent;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
}
