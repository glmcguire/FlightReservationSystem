package com.cooksys.frontend.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FormInLoopBacking {

	private String[] items = { "A", "B" };

	public String[] getItems() {
		return items;
	}

	public void action() {
		System.out.println("Action called");
	}

}