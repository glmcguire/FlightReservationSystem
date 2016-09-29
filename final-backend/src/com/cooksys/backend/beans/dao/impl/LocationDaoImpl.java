package com.cooksys.backend.beans.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.backend.beans.dao.LocationDao;
import com.cooksys.backend.model.Location;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public List<Location> getLocations() {
		Session session = factory.getCurrentSession();
		return session.createQuery("from Location").list();
	}

	@Override
	public Location getLocationById(Integer locationId) {
		Session session = factory.getCurrentSession();
		return (Location) session.createQuery("from Location l where l.locationId = :id order by city")
				.setParameter("id", locationId).uniqueResult();
	}

}
