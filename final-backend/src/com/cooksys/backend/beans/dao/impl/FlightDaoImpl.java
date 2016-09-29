package com.cooksys.backend.beans.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooksys.backend.beans.dao.FlightDao;
import com.cooksys.backend.beans.dao.LocationDao;
import com.cooksys.backend.beans.dao.UserDao;
import com.cooksys.backend.model.Booking;
import com.cooksys.backend.model.Location;
import com.cooksys.backend.model.Trip;
import com.cooksys.core.models.Flight;
import com.cooksys.core.models.FlightModel;

@Repository
@Transactional
public class FlightDaoImpl implements FlightDao {

	private Logger log = LoggerFactory.getLogger(FlightDaoImpl.class);
	private static final String GET_FLIGHT_MODEL = "http://localhost:8080/FinalInstructorWebService/getFlightModel";
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private LocationDao locationDao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserDao userDao;

	private String origin;
	private Integer originId;
	private Integer destinationId;
	private Trip rootTrip = new Trip();
	private FlightModel currentModel = new FlightModel();
	private FlightModel flightModel = new FlightModel();
	private FlightModel filteredModel = new FlightModel();
	private String destination;
	private List<Flight> currentFlights = new ArrayList<Flight>();
	private List<FlightModel> listModels = new ArrayList<FlightModel>();
	private FlightModel filteredModelID = new FlightModel();

	private List<Flight> parentFlights = new ArrayList<Flight>();

	@Override
	public FlightModel getFlightModel() {
		FlightModel newFlightModel = restTemplate.getForObject(GET_FLIGHT_MODEL, FlightModel.class);

		// TODO test or comment out
		if (flightModel != null && flightModel.getFlights() != null) {
			
//			//Quick filter to filter out flights that have arrived 2 days ago....
//			for (Flight oldFlight : flightModel.getFlights()) {
//				if(oldFlight != null){
//					if (oldFlight.getDeparture() == -1 && oldFlight.getFlightStatus().equalsIgnoreCase("Flight Arrived")) {
//						flightModel.getFlights().remove(oldFlight);
//					}
//				}
//			}

			for (Flight newFlight : newFlightModel.getFlights()) {
				Boolean found = false;
				for (Flight flight : flightModel.getFlights()) {
					if (flight.getFlightId().equals(newFlight.getFlightId())) {
						found = true;
						flight.setDeparture(newFlight.getDeparture());

					}
				}
				if (found = false) {
					flightModel.getFlights().add(newFlight);
				}

			}
		} else {
			flightModel = newFlightModel;
		}
		flightModel.getFlights().sort((f1, f2) -> Integer.compare(f1.getDeparture(), f2.getDeparture()));

		return flightModel;
	}

	@Override
	public FlightModel getFlightsByOriginCity(String city) {
		List<Flight> flights = new ArrayList<Flight>();
		log.info("CITY PASSED IN: " + city);
		log.info("GET FLIGHT MODEL AND FLIGHTS: " + flightModel.getFlights().size());
		for (Flight flight : flightModel.getFlights()) {
			if (flight.getOrigin().getCity().equalsIgnoreCase(city)) {
				log.info("PARENT FLIGHT BEING ADDED! " + flight.toString());
				flights.add(flight);
			}
		}
		filteredModel.setFlights(flights);
		return filteredModel;
	}

	public FlightModel getFlightsByOriginCityId(Integer city) {
		List<Flight> flights = new ArrayList<Flight>();
		Location origin = locationDao.getLocationById(city);
		log.info("CITY PASSED IN: " + origin.getCity());
		log.info("GET FLIGHT MODEL AND FLIGHTS: " + flightModel.getFlights().size());
		for (Flight flight : flightModel.getFlights()) {
			if (flight.getOrigin().getCity().equalsIgnoreCase(origin.getCity())) {
				log.info("PARENT FLIGHT BEING ADDED! " + flight.toString());
				flights.add(flight);
			}
		}
		filteredModelID.setFlights(flights);
		return filteredModelID;
	}

	@Override
	public FlightModel getFlightsByDestinationCity(String city) {
		List<Flight> flights = new ArrayList<Flight>();
		flightModel = new FlightModel();
		for (Flight flight : flightModel.getFlights()) {
			if (flight.getDestination().getCity().equals(city)) {
				log.info("inside if statement");
				flights.add(flight);
			}
		}
		flightModel.setFlights(flights);
		return flightModel;
	}

	// Not finished
	@Override
	public Booking bookFlight(Integer flight, Integer userId) {
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
		return result;

	}

	@Override
	public FlightModel getListOfBookedByUserId(Integer id) {
		return flightModel;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////Recursion Logic
	// Below///////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	public List<FlightModel> searchFlightsFromTo(Integer origin, Integer dest) {

		log.info("INTEGERS ORGIRIN AND THEN DESTINATION: " + origin + "----" + dest);
		this.originId = origin;

		this.destination = locationDao.getLocationById(dest).getCity();
		log.info("destination= " + destination);
		rootTrip = new Trip();
		listModels = new ArrayList<FlightModel>();

		getFlightsByOriginCityId(origin);

		log.info("PARENT FLIGHT MODEL: " + getFlightsByOriginCityId(origin).toString());

		log.info("/////////////////" + getFlightsByOriginCityId(origin).getFlights().size());

		// * Searches for all trips that start at origin and eventually end at
		// * destination

		log.info("getFlights().size(): " + filteredModelID.getFlights().size());
		for (Flight pflight : filteredModelID.getFlights()) {
			rootTrip.getTrips().add(new Trip(null, pflight, pflight.getFlightId().toString()));

			log.info("our rootTrip flight: " + pflight.toString());
		}
		log.info("rootTrip.size({})", rootTrip.getTrips().size());

		rootTrip.setTripComplete(true);

		for (Trip ptrip : rootTrip.getTrips()) {

			log.info("rootTrip's destination: " + ptrip.getFlight().getDestination().getCity());
			log.info("destination: " + ptrip.getFlight().getDestination().getCity());
			log.info("goal destination:" + destination);

			if (ptrip.getFlight().getDestination().getCity().equalsIgnoreCase(destination)) {
				log.info("complete route: {}", ptrip.getFlight());

				ptrip.setTripComplete(true);

				log.info("rootTrip getTripComplete: {}", rootTrip.getTripComplete());

			} else {
				// begin recursion
				tripTree(ptrip);
			}
		}

		log.info("trips total: " + rootTrip.getTrips().size());

		// return after recursion
		for (Trip ptrip : rootTrip.getTrips())
			filterTrips(ptrip);

		log.info("{} routes found", listModels.size());
		return listModels;
	}

	/**
	 * So here I pass in a tripwrapper and a destination city(string). First the
	 * method starts off by getting the current flight attached to the
	 * tripwrapper, this flight will be labeled our current parent flight. From
	 * there, we iterate through a list of all of the flights, first checking to
	 * see if the flight leaves the destination of our parent flight, after our
	 * parent flight lands. If true, then we check to see if that flight(which
	 * is now considered a child flight) has made it to the destination. If
	 * true, we then add that trip to a list of trips within the trip. If not,
	 * recursion, passing tempTrip back in (with a new parent flight). Also, and
	 * finally, check to see if that list of trips != 0, if true, back out
	 * (during the recursion) adding each child to their parent.
	 * 
	 * @param trip
	 */
	private void tripTree(Trip trip) {
		log.info("destination INSIDE RECURSION= " + destination);
		String flightId;
		log.info("SEARCHING from: {}", trip.getFlight().toString());
		for (Flight flight : flightModel.getFlights()) {
			log.info("Comparing to: {}", flight.toString());
			if (trip.getFlight().getDestination().getCity().equalsIgnoreCase(flight.getOrigin().getCity())
					&& trip.getFlight().getArrival() <= flight.getDeparture()) {
				flightId = trip.getFlightId() + '.' + flight.getFlightId().toString();
				Trip tempTrip = new Trip(trip, flight, flightId);
				// have we made it to target destination
				// //////////IT WAS
				// HERE//////////////////////////////////////////////
				if (flight.getDestination().getCity().equalsIgnoreCase(destination)) {// ////////////
					tempTrip.setTripComplete(true);
					log.info("Trip and Flight id: " + flightId);
					log.info("isTripComplete True: {}", trip.getTripComplete());

					trip.getTrips().add(tempTrip);
					log.info("trip complete: {}", trip.toString());
				} else {
					log.info("search for subsequent flights");
					tripTree(tempTrip);

					// return here after recursion
					if (trip.getTrips().size() != 0) {
						trip.getTrips().add(tempTrip);
					}
				}
			}
		}
	}

	/*
	 * Filters trips then adds them each to a model
	 */
	private void filterTrips(Trip trip) {
		// if direct flight to destination
		log.info("filterTrips(Trip trip) " + trip.toString());
		if (trip.getTripComplete()) {
			saveUpdatedModel(trip);
		} else {
			for (Trip loopTrip : trip.getTrips()) {
				log.info("loop trip: " + loopTrip.toString());
				if (loopTrip.getTripComplete()) {
					saveUpdatedModel(loopTrip);
				}
			}
		}
	}

	/**
	 * Sets current flights to a current model, then adds that model to a list
	 * of models (so to transfer models across to frontend
	 * 
	 * @param trip
	 */
	private void saveUpdatedModel(Trip trip) {
		currentFlights = new ArrayList<Flight>();
		currentModel = new FlightModel();
		listOutTrips(trip);

		currentModel.setFlights(currentFlights);
		log.info("currentModel.getFlights().size(): " + currentModel.getFlights().size());

		for (Flight flight : currentFlights) {
			currentModel.setTotalEta(currentModel.getTotalEta() + flight.getEta());
		}

		// for(Flight flight : currentFlights){
		// //settting total eta here
		// }
		listModels.add(currentModel);
		log.info("listModels.size(): " + listModels.size());
	}

	/**
	 * Flattens out trip tree, adds all flights to a flight list
	 * 
	 * @param trip
	 */
	private void listOutTrips(Trip trip) {
		if (trip.getParent() != null) {
			listOutTrips(trip.getParent());
		}
		// return here after recursion
		currentFlights.add(trip.getFlight());
	}

}
