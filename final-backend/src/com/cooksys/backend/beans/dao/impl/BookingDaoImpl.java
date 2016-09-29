package com.cooksys.backend.beans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cooksys.backend.beans.dao.BookingDao;
import com.cooksys.backend.beans.dao.FlightDao;
import com.cooksys.backend.beans.dao.UserDao;
import com.cooksys.backend.model.Booking;
import com.cooksys.core.models.Flight;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class BookingDaoImpl implements BookingDao {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FlightDao flightDao;

	@Override
	public List<Booking> getBookings() {
		log.info("getBooking()");
		return sessionFactory.getCurrentSession().createQuery("from Booking").list();
	}

	@Override
	public Booking getBookingById(int bookingId) {
		log.info("getBookingById({})", bookingId);
		return (Booking) sessionFactory.getCurrentSession().createQuery(" from Booking where booking_id = :bookingId ")
				.setInteger("bookingId", bookingId).uniqueResult();
	}

	@Override
	public Booking getBookingByBooking(int flightId, int userId) {
		log.info("getBookingByBooking(flight: {}, user: {})", flightId, userId);
		return (Booking) sessionFactory.getCurrentSession()
				.createQuery(" from Booking where userId = :userId and flight_id = :flightId  ")
				.setInteger("flightId", flightId).setInteger("userId", userId).uniqueResult();
	}

	@Override
	public List<Booking> getBookingsByUserId(int userId) {
		log.info("getBookingByUserId({})", userId);
		return sessionFactory.getCurrentSession().createQuery(" from Booking where user_id = :userId ")
				.setInteger("userId", userId).list();
	}

	@Override
	public List<Flight> getBookedFlightsByUserId(int userId) {
		List<Flight> flights = new ArrayList<>();
		List<Flight> bookedFlights = new ArrayList<>();

		flights = flightDao.getFlightModel().getFlights();

		for (Booking booking : getBookingsByUserId(userId))
			for (Flight flight : flights)
				if (booking.getFlightId() == flight.getFlightId())
					bookedFlights.add(flight);

		return bookedFlights;
	}

	@Override
	public void deleteBookings() {
		log.info("deleteBookings()");
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("delete from Booking").executeUpdate();
	}

	@Override
	public void deleteBooking(int flightId, int userId) {
		log.info("deleteBooking(flight: {}, user: {})", flightId, userId);
		Session session = sessionFactory.getCurrentSession();
		Booking booking = (Booking) sessionFactory.getCurrentSession()
				.createQuery(" from Booking where flight_id = :flightId and id = :userId ")
				.setInteger("flightId", flightId).setInteger("userId", userId).uniqueResult();
		session.delete(booking);
	}

	@Override
	public void saveBookings(List<Integer> flightIds, int userId) {
		log.info("saveBookings(flightIds: {}, userId: {})", flightIds.size(), userId);
		Session session = sessionFactory.getCurrentSession();
		log.info(userDao.getUserById(userId).getEmail());
		for (Integer flightId : flightIds) {
			Booking booking = getBookingByBooking(flightId, userId);
			if (booking == null)
				booking = new Booking(flightId, userId);
			session.save(booking);
		}
	}

	@Override
	public void bookFlight(Integer flight, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Booking result = new Booking();
		try {
			result.setFlightId(flight);
			result.setUserId(userId);
			session.save(result);

			log.info("Successfully registered user.");

		} catch (Throwable t) {
			log.warn("Could not register user", t);
		}

	}

}