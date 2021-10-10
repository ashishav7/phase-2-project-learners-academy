package com.learners.web.learnerClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learners.model.LearnerClass;
import com.learners.model.Student;
import com.learners.model.Subject;
import com.learners.util.HibernateUtil;

@WebServlet("/add-class")
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddClass() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("class.html").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("home.html").include(request, response);
		
		try {
			SessionFactory factory = HibernateUtil.buildSessionFactory();
			
			Session session = factory.openSession();
			
			Transaction t = session.beginTransaction();
			
			LearnerClass learnerClass = new LearnerClass(request.getParameter("className"),request.getParameter("smartClass"));
			
			Set<Subject> subjects = new HashSet<Subject>();
			Set<Student> students = new HashSet<Student>();
			for(int i=1;i<=Integer.parseInt(request.getParameter("noOfSubjects"));i++) {
				Subject s = new Subject(request.getParameter("subject"+i),request.getParameter("subjectCode" + i));
				subjects.add(s);
			}
			
			for(int i=1;i<=Integer.parseInt(request.getParameter("noOfStudents"));i++) {
				Student s = new Student(request.getParameter("student"+i),request.getParameter("studentRoll"+i),request.getParameter("studentAddress"+i));
				s.setSubject(subjects);
				students.add(s);
			}
			
			learnerClass.setStudents(students);
			learnerClass.setSubject(subjects);
			
			session.save(learnerClass);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Class is created sucessfully ! </h3>");
			}

			
			session.close();
			
		} catch (Exception e) {
			out.println("<h3 style = 'color:red'> Error </h3>");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
