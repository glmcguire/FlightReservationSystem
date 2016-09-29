package com.cooksys.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.backend.beans.dao.BookingDao;
import com.cooksys.backend.beans.dao.FlightDao;
import com.cooksys.backend.beans.dao.LocationDao;
import com.cooksys.backend.beans.dao.UserDao;
import com.cooksys.backend.beans.wrappers.BookingWrapper;
import com.cooksys.backend.beans.wrappers.FlightModelWrapper;
import com.cooksys.backend.beans.wrappers.FlightWrapper;
import com.cooksys.backend.beans.wrappers.Wrapper;
import com.cooksys.backend.model.Booking;
import com.cooksys.backend.model.Location;
import com.cooksys.backend.model.User;
import com.cooksys.core.models.FlightModel;

@RestController
public class Controller {
	private Logger log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private FlightDao flightDao;

	@Autowired
	private BookingDao bookingDao;

	// /////////////////////////////////////////////////////
	// ////////////////////Users////////////////////////////
	// /////////////////////////////////////////////////////

	@RequestMapping(value = "/user", method = RequestMethod.PATCH)
	public User changeUserPassword(@RequestHeader String key, @RequestBody UserAuthentication userPass) {
		User user = userDao.getUserByName(userPass.getUser());
		User result = userDao.updateUser(user);
		return result;
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String username) {
		User result = userDao.getUserByName(username);
		return result;
	}

	@RequestMapping(value = "/userEmail/{email}", method = RequestMethod.GET)
	public @ResponseBody User getUserByEmail(@PathVariable String email) {
		User result = userDao.getUserByEmail(email);
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		userDao.registerUser(user);
		return user;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		userDao.updateUser(user);
		return user;
	}

	// /////////////////////////////////////////////////////
	// ////////////////////Locations////////////////////////
	// /////////////////////////////////////////////////////

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public @ResponseBody Wrapper getLocations() {
		Wrapper wrapper = new Wrapper(locationDao.getLocations());
		return wrapper;
	}

	//
	@RequestMapping(value = "/locations/{locationId}", method = RequestMethod.GET)
	public @ResponseBody Location getLocationById(@PathVariable Integer locationId) {
		Location location = locationDao.getLocationById(locationId);
		return location;
	}

	// Changing to ID
	@RequestMapping(value = "/trip/{origin}/{destination}", method = RequestMethod.GET)
	public @ResponseBody FlightModelWrapper tripSearch(@PathVariable("origin") Integer origin,
			@PathVariable("destination") Integer destination) {
		FlightModelWrapper wrapper = new FlightModelWrapper(flightDao.searchFlightsFromTo(origin, destination));
		return wrapper;
	}

	// /////////////////////////////////////////////////////
	// ////////////////////FlightModel//////////////////////
	// /////////////////////////////////////////////////////

	@RequestMapping(value = "/flightmodel", method = RequestMethod.GET)
	public @ResponseBody FlightModel getFlightModel() {
		FlightModel result = flightDao.getFlightModel();
		return result;
	}

	@RequestMapping(value = "/flightmodel/origin/{city}", method = RequestMethod.GET)
	public @ResponseBody FlightModel getFlightModelByOriginCity(@PathVariable String city) {
		FlightModel result = flightDao.getFlightsByOriginCity(city);
		return result;
	}

	@RequestMapping(value = "/flightmodel/destination/{city}", method = RequestMethod.GET)
	public @ResponseBody FlightModel getFlightModelByDestinationCity(@PathVariable String city) {
		FlightModel result = flightDao.getFlightsByDestinationCity(city);
		return result;
	}

	// /////////////////////////////////////////////////////
	// //////////////////BookedFlights//////////////////////
	// /////////////////////////////////////////////////////

	@RequestMapping(value = "/bookflight/{userid}/{flightId}", method = RequestMethod.POST)
	public void bookFlight(@RequestBody Integer flight, @RequestBody Integer userId) {
		bookingDao.bookFlight(flight, userId);
	}

	@RequestMapping(value = "/bookedflights/user/{id}", method = RequestMethod.GET)
	public @ResponseBody FlightModel getListOfBookedByUserId(@PathVariable Integer id) {
		FlightModel result = flightDao.getListOfBookedByUserId(id);
		return result;
	}

	@RequestMapping(value = "/getBookingById/{bookingId}", method = RequestMethod.GET)
	public @ResponseBody Booking getBookingById(@PathVariable int bookingId) {
		return bookingDao.getBookingById(bookingId);
	}

	@RequestMapping(value = "/getBookingsByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody BookingWrapper getBookingsByUserId(@PathVariable int userId) {
		return new BookingWrapper(bookingDao.getBookingsByUserId(userId));
	}

	@RequestMapping(value = "/getBookedFlightsByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody FlightWrapper getBookedFlightsByUserId(@PathVariable int userId) {
		FlightWrapper flightWrapper = new FlightWrapper();
		flightWrapper.setListFlights(bookingDao.getBookedFlightsByUserId(userId));
		return flightWrapper;
	}

	@RequestMapping(value = "/deleteBooking/{flightId}/{userId}", method = RequestMethod.POST)
	public @ResponseBody void deleteBooking(@PathVariable int flightId, @PathVariable int userId) {
		bookingDao.deleteBooking(flightId, userId);
	}

	@RequestMapping(value = "/saveBookings/{userId}", method = RequestMethod.POST)
	public @ResponseBody void saveBookings(@RequestBody FlightWrapper flightWrapper, @PathVariable int userId) {
		bookingDao.saveBookings(flightWrapper.getListFlightIds(), userId);
	}

}
