package com.cooksys.frontend.beans.dao;

import java.util.List;

import com.cooksys.frontend.model.Booking;
import com.cooksys.frontend.model.Flight;

public interface BookingDao {

	public List<Booking> getBookings();

	public Booking getBookingById(int bookingId);

	public List<Booking> getBookingsByUserId();

	public void deleteBooking(int flightId);

	public void saveBookings(List<Integer> flightIds);

	public List<Flight> getBookedFlightsByUserId();

}
