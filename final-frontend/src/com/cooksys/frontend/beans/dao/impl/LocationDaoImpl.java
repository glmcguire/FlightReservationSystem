package com.cooksys.frontend.beans.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.beans.dao.LocationDao;
import com.cooksys.frontend.beans.wrappers.Wrapper;
import com.cooksys.frontend.model.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
	private RestTemplate restTemplate = new RestTemplate();
	private Logger log = LoggerFactory.getLogger(LocationDaoImpl.class);

	@Override
	public List<Location> getOrigins() {
		@SuppressWarnings("unchecked")
		Wrapper wrapper = restTemplate.getForObject(
				"http://localhost:8080/final-backend/locations", Wrapper.class);
		return wrapper.getItems();
	}

	@Override
	public List<Location> getLocationById(Integer id) {
		@SuppressWarnings("unchecked")
		Wrapper wrapper = restTemplate.getForObject(
				"http://localhost:8080/final-backend/locations/" + id, Wrapper.class);
		return wrapper.getItems();
	}

}
