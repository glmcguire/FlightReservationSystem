package com.cooksys.backend.beans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.core.models.FlightModel;

@XmlRootElement
public class FlightModelWrapper {
	private List<FlightModel> items;

	public FlightModelWrapper() {
		items = new ArrayList<FlightModel>();
	}

	public FlightModelWrapper(List<FlightModel> items) {
		this.items = items;
	}

	@XmlElement(name = "items")
	public List<FlightModel> getItems() {
		return items;
	}
}
