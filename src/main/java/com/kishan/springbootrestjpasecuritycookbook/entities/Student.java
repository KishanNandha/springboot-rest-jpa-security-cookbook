package com.kishan.springbootrestjpasecuritycookbook.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Long id;

	@Basic(optional = false)
	@Column(name = "firstname", nullable = false, length = 50)
	private String firstname;
	
	@Basic(optional = true)
	@Column(nullable = true, length = 50)
	private String lastname;
	
	@Email(message = "Enter valid email")
	@Basic(optional = false)
	@Column(nullable = false, length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TeacherType type;

	@Past
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dob;
	
	//Effectively, in a database, a Many-to-Many relationship involves a middle table referencing both other tables.
	//we'll also be using a @JoinTable annotation to set up the table that represents the relationship:
	
	//The first parameter, joinColumns defines how to configure the join column (foreign key) of the owning side of the relationship in the table. In this case, the owning side is a student.
	//On the other hand, the inverseJoinColumns parameter does the same, but for the referencing side (course).
	
	
	@ManyToMany
	@JoinTable(name="studentscourses", joinColumns = @JoinColumn(name="studentid", referencedColumnName = "id")
									, inverseJoinColumns = @JoinColumn(name="courseid", referencedColumnName = "id"))
	List<Course> courses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TeacherType getType() {
		return type;
	}

	public void setType(TeacherType type) {
		this.type = type;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
