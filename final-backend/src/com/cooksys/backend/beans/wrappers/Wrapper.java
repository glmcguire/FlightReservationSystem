package com.cooksys.backend.beans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cooksys.backend.model.Location;


@XmlRootElement
public class Wrapper {
	 
    private List<Location> items;
 
    public Wrapper() {
        items = new ArrayList<Location>();
    }
 
    public Wrapper(List<Location> items) {
        this.items = items;
    }
 
    @XmlElement( name="items" )
    public List<Location> getItems() {
        return items;
    }
}
