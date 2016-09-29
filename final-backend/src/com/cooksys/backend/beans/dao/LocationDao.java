package com.cooksys.backend.beans.dao;

import java.util.List;

import com.cooksys.backend.model.Location;

public interface LocationDao {
	public List<Location> getLocations();
	public Location getLocationById(Integer locationId);
	
}
