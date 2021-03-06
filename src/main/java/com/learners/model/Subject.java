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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name ="subject")
public class Subject {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "sub_id")
	private int id;
	
	@Column(name= "subject_name")
	private String name;
	
	@Column(name= "subject_code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Subject(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Subject(String name,String code) {
		super();
		this.name = name;
		this.code = code;
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

	public Subject() {
		super();
	}
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
