package com.cooksys.frontend.beans.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.beans.dao.FlightDao;
import com.cooksys.frontend.model.Flight;
import com.cooksys.frontend.model.FlightModel;

@Repository
public class FlightDaoImpl implements FlightDao {
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public Flight bookFlight(Integer flight) {
		return restTemplate.postForObject(
				"http://localhost:8080/final-backend/bookflight", flight,
				Flight.class);

	}

	@Override
	public FlightModel getListOfBookedByUserId(Integer id) {
		return restTemplate.getForObject(
				"http://localhost:8080/final-backend/bookedflights/user/" + id,
				FlightModel.class);
	}
	
	

}
