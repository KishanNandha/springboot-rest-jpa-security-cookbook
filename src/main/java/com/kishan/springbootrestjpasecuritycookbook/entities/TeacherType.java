package com.kishan.springbootrestjpasecuritycookbook.entities;

public enum TeacherType {

	FullTime("full-time"),PartTime("part-time")
	,Contract("contract");
	
	private String name;
	
	private TeacherType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
