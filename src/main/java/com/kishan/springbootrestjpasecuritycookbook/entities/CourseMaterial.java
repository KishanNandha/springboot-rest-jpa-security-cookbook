package com.kishan.springbootrestjpasecuritycookbook.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "coursematerials", uniqueConstraints = {@UniqueConstraint(columnNames = {"data","type","courseId"})})
public class CourseMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Long id;

	@Basic(optional = false)
	@Column(name = "data", nullable = false, length = 250)
	private String data;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CourseMaterialType type;

	//There is no point in having material without a course to encompass it. That’s why the relationship is not optional in that direction.
	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "courseId", referencedColumnName = "id")
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CourseMaterialType getType() {
		return type;
	}

	public void setType(CourseMaterialType type) {
		this.type = type;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
