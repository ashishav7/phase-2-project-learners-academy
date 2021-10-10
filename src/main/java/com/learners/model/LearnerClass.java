package com.learners.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name ="learners_class")
public class LearnerClass {
	// class - student one class can have multiple students one to many mapping
	// class - subject one class will have one subject one to one mapping
	// teacher -student one teacher can have multiple students one to many mapping
	// student -subject many student can have multiple students many to many mapping
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private int id;
	
	@Column(name= "class_name")
	private String name;
	
	@Column(name= "smart_class")
	private String smartClass;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Subject> subject;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Student> students;

	
	public LearnerClass(int id, String name,String smartClass) {
		super();
		this.id = id;
		this.name = name;
		this.smartClass = smartClass;
	}
	
	public LearnerClass(String name,String smartClass) {
		super();
		this.name = name;
		this.smartClass = smartClass;
	}

	
	public String getSmartClass() {
		return smartClass;
	}


	public void setSmartClass(String smartClass) {
		this.smartClass = smartClass;
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

	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public LearnerClass(String name) {
		super();
		this.name = name;
	}

	public LearnerClass() {
		super();
	}
	
	
	
}
