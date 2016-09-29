package com.cooksys.frontend.beans.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.beans.AuthenticationBean;
import com.cooksys.frontend.beans.dao.BookingDao;
import com.cooksys.frontend.beans.wrappers.BookingWrapper;
import com.cooksys.frontend.beans.wrappers.FlightWrapper;
import com.cooksys.frontend.model.Booking;
import com.cooksys.frontend.model.Flight;

@Repository
public class BookingDaoImpl implements BookingDao {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	public final static String GET_BOOKINGS_URL = "http://localhost:8080/final-backend/getBookings";
	public final static String GET_BOOKINGS_BY_ID_URL = "http://localhost:8080/final-backend/getBookingById/";
	public final static String GET_BOOKED_FLIGHTS_BY_USERID_URL = "http://localhost:8080/final-backend/getBookedFlightsByUserId/";
	public final static String GET_BOOKINGS_BY_USERID_URL = "http://localhost:8080/final-backend/getBookingsByUserId/";
	public final static String DELETE_BOOKING_URL = "http://localhost:8080/final-backend/deleteBooking/";
	public final static String SAVE_BOOKINGS_URL = "http://localhost:8080/final-backend/saveBookings/";
	public final static String BOOK_FLIGHT = "http://localhost:8080/final-backend/bookflight/";

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private AuthenticationBean authenticationBean;
	private int userId;

	public void getUserId() {
		log.info("getUserId()");
		if (authenticationBean.getUser() != null)
			this.userId = authenticationBean.getUser().getId();
	}

	@Override
	public List<Booking> getBookings() {
		return restTemplate.getForObject(GET_BOOKINGS_URL, BookingWrapper.class).getBookings();
	}

	@Override
	public Booking getBookingById(int bookingId) {
		return restTemplate.getForObject(GET_BOOKINGS_BY_ID_URL + bookingId, Booking.class);
	}

	@Override
	public List<Flight> getBookedFlightsByUserId() {
		getUserId();
		return restTemplate.getForObject(GET_BOOKED_FLIGHTS_BY_USERID_URL + userId, FlightWrapper.class)
				.getListFlights();
	}

	@Override
	public List<Booking> getBookingsByUserId() {
		Integer authUserId = authenticationBean.getUser().getId();
		return restTemplate.getForObject(GET_BOOKINGS_BY_USERID_URL + authUserId, BookingWrapper.class).getBookings();
	}

	@Override
	public void deleteBooking(int flightId) {
		getUserId();
		restTemplate.postForObject(DELETE_BOOKING_URL + flightId + "/" + userId, null, String.class);
	}

	@Override
	public void saveBookings(List<Integer> flightIds) {
		getUserId();
		FlightWrapper flightWrapper = new FlightWrapper();
		flightWrapper.setListFlightIds(flightIds);
		restTemplate.postForEntity(SAVE_BOOKINGS_URL + userId, flightWrapper, FlightWrapper.class);
	}
	

}