package com.cooksys.frontend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cooksys.frontend.beans.dao.BookingDao;
import com.cooksys.frontend.beans.dao.FlightDao;
import com.cooksys.frontend.beans.dao.LocationDao;
import com.cooksys.frontend.beans.wrappers.FlightModelWrapper;
import com.cooksys.frontend.model.Booking;
import com.cooksys.frontend.model.Flight;
import com.cooksys.frontend.model.FlightModel;
import com.cooksys.frontend.model.Location;

@Component
@ViewScoped
public class FlightModelBean {
	private Logger log = LoggerFactory.getLogger(FlightModelBean.class);
	private static final String GET_FLIGHT_MODEL = "http://localhost:8080/final-backend/flightmodel";
	private static final String GET_FLIGHT_MODEL_ORIGIN_CITY = "http://localhost:8080/final-backend/flightmodel/origin/";
	private static final String GET_FLIGHT_MODEL_DESTINATION_CITY = "http://localhost:8080/final-backend/flightmodel/destination/";
	private static final String GET_FLIGHT_MODEL_ORIGIN_CITY_ID = "http://localhost:8080/final-backend/flightmodel/originId/";
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private LocationDao locationDao;
	@Autowired
	private FlightDao flightDao;
	@Autowired
	private BookingDao bookingDao;

	private Integer locationId = null;
	private List<Flight> flights;
	private FlightModel flightModel;
	private List<Location> locations;
	private List<Location> origins;
	private List<Location> searchLocations;
	private Location origin = new Location();
	private Location destination = new Location();
	private String cityOrigin = null;
	private String cityDestination = null;
	private Flight flight;
	private List<FlightModel> listModels = new ArrayList<FlightModel>();
	private String txt1;
	private String txt2;
	private Integer id1;
	private Integer id2;
	private List<Flight> bookedFlights = new ArrayList<Flight>();
	private List<FlightModel> searchedModels = new ArrayList<FlightModel>();

	@PostConstruct
	public void init() {
		flightModel = new FlightModel();
		searchLocations = new ArrayList<Location>();

	}
	
	public List<Flight> getBookedFights(){
		List<Booking> returns = bookingDao.getBookingsByUserId();
		List<Flight> results = new ArrayList<Flight>();
		List<Flight> flights = getFlightModel().getFlights();
		for(Booking books : returns){
			for (Flight flight : flights){
				if (books.getFlightId() == flight.getFlightId()){
					results.add(flight);
				}
			}
		}
		
		return results;
		
	}
	
	
	public String testButton(){
		System.out.println("BUTTON PRESSED!");
		return "yes";
	}
	
	public String bookFlight(){
		log.info("Calling bookFlight()!!!!");
		System.out.println("Pressing button");
		List<Integer> flightId = new ArrayList<>();
		List<Flight> flights = new ArrayList<Flight>();
		List<FlightModel> search = getSearchedModels();
		for(FlightModel fm : search){
			flights = fm.getFlights();
			for(Flight flight : flights){
				flightId.add(flight.getFlightId());
				log.info("Booking flight to : " + flight.getDestination().getCityState());
			}
		}
		
		
		bookingDao.saveBookings(flightId);
		return "flight(s) booked";
	}

	public FlightModel getFlightModel() {

		return restTemplate.getForObject(GET_FLIGHT_MODEL, FlightModel.class);
	}

	public FlightModel getFlightModelByOriginCity(String txt1) {
		return restTemplate.getForObject(GET_FLIGHT_MODEL_ORIGIN_CITY + txt1, FlightModel.class);
	}

	public FlightModel getFlightModelByOriginCityId(Integer id) {
		return restTemplate.getForObject(GET_FLIGHT_MODEL_ORIGIN_CITY_ID + id, FlightModel.class);
	}

	public FlightModel getFlightModelByDestinationCity(String txt1) {
		return restTemplate.getForObject(GET_FLIGHT_MODEL_DESTINATION_CITY + txt1, FlightModel.class);
	}

	public List<Flight> getFlightsByOriginCity(String txt1) {
		log.info("city coming in: " + txt1);
		return getFlightModelByOriginCity(txt1).getFlights();

	}

	public List<Flight> getFlightsByDestinationCity(String txt1) {
		log.info("city coming in: " + txt1);
		return getFlightModelByDestinationCity(txt1).getFlights();

	}

	public List<FlightModel> searchTrips() {
		this.id1 = getId1();
		this.id2 = getId2();

		log.info("http://localhost:8080/final-backend/trip/" + txt1 + "/" + txt2);

		// /changed to integer
		@SuppressWarnings("unchecked")
		FlightModelWrapper wrapper = restTemplate.getForObject("http://localhost:8080/final-backend/trip/" + id1 + "/"
				+ id2, FlightModelWrapper.class);
		this.listModels = wrapper.getItems();
		log.info(listModels.toString());
		this.searchedModels = listModels;
		return listModels;
	}

	public List<Flight> getFlights() {
		return getFlightModel().getFlights();
	}

	public void bookFlight(Integer flight) {
		flightDao.bookFlight(flight);
	}

	public Boolean isOriginSearch() {
		if (cityOrigin != null) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean isDestinationSearch() {
		if (cityDestination != null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isSearch() {
		if (cityDestination != null && cityOrigin != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public List<Location> getLocations() {
		locations = locationDao.getOrigins();
		return locations;
	}

	public String getCityOrigin() {
		return cityOrigin;
	}

	public void setCityOrigin(String cityOrigin) {
		this.cityOrigin = cityOrigin;
	}

	public String getCityDestination() {
		return cityDestination;
	}

	public void setCityDestination(String cityDestination) {
		this.cityDestination = cityDestination;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<FlightModel> getListModels() {
		return listModels;
	}

	public void setListModels(List<FlightModel> listModels) {
		this.listModels = listModels;
	}

	public List<Location> getOrigins() {
		origins = locationDao.getOrigins();
		return origins;
	}

	public List<FlightModel> getSearchedModels() {
		return searchedModels;
	}

	public void setSearchedModels(List<FlightModel> searchedModels) {
		this.searchedModels = searchedModels;
	}

	public void setOrigins(List<Location> origins) {
		this.origins = origins;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public String getTxt2() {
		return txt2;
	}

	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	// Select down buttons Logic
	public List<String> completeSearch() {
		List<String> results = new ArrayList<String>();
		List<Location> locations = new ArrayList<Location>();
		locations = this.getLocations();
		for (Location location : locations) {
			results.add(location.getCity());
		}

		return results;
	}

	// //////////////////////
	public Integer getId1() {
		Integer result = null;
		for (Location test : getLocations()) {
			if (test.getCity().equalsIgnoreCase(getTxt1())) {
				result = test.getLocationId();
			}
		}
		return result;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public Integer getId2() {
		Integer result = null;
		for (Location test : getLocations()) {
			if (test.getCity().equalsIgnoreCase(getTxt2())) {
				result = test.getLocationId();
			}
		}
		return result;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

}
