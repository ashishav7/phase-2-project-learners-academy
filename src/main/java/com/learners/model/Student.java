package com.learners.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private int id;
	
	@Column(name= "student_name")
	private String name;
	
	@Column(name= "student_rollNo")
	private String rollNo;
	
	@Column(name= "student_address")
	private String address;
	
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="students_subjects", joinColumns=@JoinColumn(name="id"),
	inverseJoinColumns=@JoinColumn(name="sub_id"))
	private Set<Subject> subject;
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(int id, String name, String rollNo, String address) {
		super();
		this.id = id;
		this.name = name;
		this.rollNo = rollNo;
		this.address = address;
	}

	public Student(String name, String rollNo, String address) {
		super();
		this.name = name;
		this.rollNo = rollNo;
		this.address = address;
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

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

	public Student() {
		super();
	}
	
}
