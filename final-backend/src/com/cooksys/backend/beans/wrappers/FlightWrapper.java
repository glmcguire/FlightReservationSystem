package com.cooksys.backend.beans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.core.models.Flight;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightWrapper {

	@XmlElement
	private List<Flight> listFlights = new ArrayList<>();
	private List<Integer> listFlightIds = new ArrayList<>();

	public FlightWrapper() {
	}

	public List<Flight> getListFlights() {
		return listFlights;
	}

	public void setListFlights(List<Flight> listFlights) {
		this.listFlights = listFlights;
	}

	public List<Integer> getListFlightIds() {
		return listFlightIds;
	}

	public void setListFlightIds(List<Integer> listFlightIds) {
		this.listFlightIds = listFlightIds;
	}

}
