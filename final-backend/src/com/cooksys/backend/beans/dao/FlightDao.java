package com.cooksys.backend.beans.dao;

import java.util.List;

import com.cooksys.backend.model.Booking;
import com.cooksys.core.models.FlightModel;

public interface FlightDao {

	
	public FlightModel getFlightModel();
	public FlightModel getFlightsByOriginCity(String city);
	public FlightModel getFlightsByDestinationCity(String city);
	public List<FlightModel> searchFlightsFromTo(Integer origin,
			Integer destination);

	public Booking bookFlight(Integer flight, Integer userId);
	public FlightModel getListOfBookedByUserId(Integer id);
//
}
