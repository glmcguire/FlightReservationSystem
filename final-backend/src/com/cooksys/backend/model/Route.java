package com.cooksys.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.core.models.Flight;

public class Route {

	private String tripNumber;
	private Flight flight;
	private List<Route> routes = new ArrayList<>();
	private Route parent = null;
	private Boolean root = false;
	private Boolean finalDestination = false;

	public Route() {
	}
	
	public Route(Route parent, Flight flight, String tripNumber) {
		super();
		this.parent = parent;
		this.flight = flight;
		this.tripNumber = tripNumber;
		this.root = (parent == null);
	}

	@Override
	public String toString() {
		return "[tripNumber=" + tripNumber + "]\n\tfrom:"
				+ flight.getOrigin().getCity() + "@" + flight.getDeparture()
				+ " - to:" + flight.getDestination().getCity() + "@"
				+ flight.getArrival() + "]";
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}

	public Route getParent() {
		return parent;
	}

	public void setParent(Route parent) {
		this.parent = parent;
	}

	public Boolean isRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public Boolean isFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Boolean finalDestination) {
		this.finalDestination = finalDestination;
	}

}
