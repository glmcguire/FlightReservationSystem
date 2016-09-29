package com.cooksys.backend.beans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.backend.model.Booking;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingWrapper {

	@XmlElement
	private List<Booking> bookings;

	public BookingWrapper() {
		bookings = new ArrayList<Booking>();
	}

	public BookingWrapper(List<Booking> Bookings) {
		this.bookings = Bookings;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

}