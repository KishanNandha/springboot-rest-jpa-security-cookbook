package com.kishan.springbootrestjpasecuritycookbook.entities;

public enum CourseMaterialType {

	BOOK("book"), LINK("link");
	
	private String name;

	private CourseMaterialType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
