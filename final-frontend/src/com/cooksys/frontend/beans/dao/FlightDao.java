package com.cooksys.frontend.beans.dao;

import com.cooksys.frontend.model.Flight;
import com.cooksys.frontend.model.FlightModel;

public interface FlightDao {
	public Flight bookFlight(Integer flight);
	public FlightModel getListOfBookedByUserId(Integer id);
}
