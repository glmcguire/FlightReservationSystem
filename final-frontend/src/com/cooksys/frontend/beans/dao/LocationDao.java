package com.cooksys.frontend.beans.dao;

import java.util.List;

import com.cooksys.frontend.model.Location;

public interface LocationDao {
	public List<Location> getOrigins();
	public List<Location> getLocationById(Integer id);
	
}
