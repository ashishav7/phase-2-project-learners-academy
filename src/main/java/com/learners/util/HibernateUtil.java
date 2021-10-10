package com.learners.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learners.model.LearnerClass;
import com.learners.model.Student;
import com.learners.model.Subject;
import com.learners.model.Teacher;

public class HibernateUtil {
	private static SessionFactory factory;
	public static SessionFactory buildSessionFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(LearnerClass.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Subject.class)
				.addAnnotatedClass(Teacher.class)
				.buildSessionFactory();
		return factory;
	}

}
