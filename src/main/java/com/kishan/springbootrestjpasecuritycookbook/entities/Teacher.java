package com.kishan.springbootrestjpasecuritycookbook.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

@Entity
@Table(name="teachers")
public class Teacher {
	
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
	
	
	@Email(message = "Enter valid email") // comes from javax.validation
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
	
	// for this type of relationship, we must use a foreign key in a table.
	//This is done via the @JoinColumn annotation
	//Using this annotation will tell JPA that the COURSE table must have a foreign key column TEACHER_ID that references the TEACHER table's ID column
	//Teacher class is called the owning side of the One-To-Many relationship. This is because it defines the join column between the two tables.
	//The Course is called the referencing side in that relationship.
	// It’s a good practice to put the owning side of a relationship in the class/table where the foreign key will be held. so moving @JoinColum to courses
	// use mappedBy for bi-directional 
	//Without mappedBy, we wouldn’t have a two-way relationship. We’d have two one-way relationships. Both entities would be mapping foreign keys for the other entity.
	//With mappedBy, we’re telling JPA that the field is already mapped by another entity. It’s mapped by the teacher field of the Course entity.
	
	//Eager vs Lazy Loading:
	//JPA thought ahead and made One-to-Many relationships load lazily by default.
	// In our example, that would mean until we call on the Teacher#courses method, the courses are not being fetched from the database.
	
	//By contrast, Many-to-One relationships are eager by default, meaning the relationship is loaded at the same time the entity is.
	//We can change these characteristics by setting the fetch argument of both annotations:
	
	//Optionality:
    //A relationship may be optional or mandatory.
	//Considering the One-to-Many side — it is always optional, and we can’t do anything about it. The Many-to-One side, on the other hand, offers us the option of making it mandatory.
	//By default, the relationship is optional, meaning we can save a Course without assigning it a teacher:
	//@ManyToOne(optional = false)
	
	//Cascading Operations
	//When we perform some action on the target entity, the same action will be applied to the associated entity.
	//CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
	// Cascade Type PERSIST propagates the persist operation from a parent to a child entity. 
	//CascadeType.REMOVE propagates the remove operation from parent to child entity. Similar to JPA's CascadeType.REMOVE, we have CascadeType.DELETE, which is specific to Hibernate.
	//There are multiple types of cascading operations: PERSIST, MERGE, REMOVE, REFRESH, DETACH, and ALL (that combines all the previous ones).
	
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "teacherId", referencedColumnName = "id")
	private List<Course> courses;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return Objects.equals(id, other.id);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
