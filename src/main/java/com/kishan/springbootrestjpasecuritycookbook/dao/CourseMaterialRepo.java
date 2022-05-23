package com.kishan.springbootrestjpasecuritycookbook.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kishan.springbootrestjpasecuritycookbook.entities.Course;
import com.kishan.springbootrestjpasecuritycookbook.entities.CourseMaterial;

public interface CourseMaterialRepo extends JpaRepository<CourseMaterial, Long> {

	public Optional<CourseMaterial> findById(Long id);

	List<Course> findByNameAndId(String name, Long id);

	List<Course> findByName(String name);

	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);

	@Query("Select  c  From Course c where name like '%100 Steps'")
	List<Course> courseWith100StepsInName();

	@Query(value = "Select  *  From Course c where name like '%100 Steps'", nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name = "query_get_100_Step_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}
