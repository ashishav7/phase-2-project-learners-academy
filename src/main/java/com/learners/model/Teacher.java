package com.learners.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Teacher")
public class Teacher {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private int id;
	
	@Column(name= "teacher_name")
	private String name;
	
	public Teacher() {
		super();
	}

	@Column(name= "teacher_rating")
	private int rating;
	
	@Column(name= "specialization")
	private String specialization;
	
	public Teacher(int id, String name, int rating, String specialization, Subject subject) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.specialization = specialization;
		this.subject = subject;
	}

	public Teacher(int id, String name, int rating, String specialization) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.specialization = specialization;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Teacher(String name, int rating, String specialization) {
		super();
		this.name = name;
		this.rating = rating;
		this.specialization = specialization;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sub_id")
	private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", rating=" + rating + ", specialization=" + specialization
				+ "]";
	}
}
