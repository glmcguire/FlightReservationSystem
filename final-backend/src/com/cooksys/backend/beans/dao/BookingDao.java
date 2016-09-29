package com.cooksys.backend.beans.dao;

import java.util.List;

import com.cooksys.backend.model.Booking;
import com.cooksys.core.models.Flight;

public interface BookingDao {

	public List<Booking> getBookings();

	public Booking getBookingById(int bookingId);

	public Booking getBookingByBooking(int flightId, int userId);

	public List<Booking> getBookingsByUserId(int userId);

	public void deleteBookings();

	public void deleteBooking(int flightId, int userId);

	public void saveBookings(List<Integer> flightIds, int userId);

	public List<Flight> getBookedFlightsByUserId(int userId);

	public void bookFlight(Integer flight, Integer userId);
	

}
