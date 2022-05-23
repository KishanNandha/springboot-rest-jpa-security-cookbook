package com.kishan.springbootrestjpasecuritycookbook.dao;

import org.springframework.stereotype.Repository;

import com.kishan.springbootrestjpasecuritycookbook.entities.CourseMaterial;

@Repository
public class CourseMaterialDao extends PersistanceDao<CourseMaterial, Long>{

	public CourseMaterialDao() {
		super(CourseMaterial.class);
	}

	
}
