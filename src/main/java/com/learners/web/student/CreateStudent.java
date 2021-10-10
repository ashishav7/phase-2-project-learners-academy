package com.learners.web.student;

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

@WebServlet("/create-student")
public class CreateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreateStudent() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("create-student.html").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		
		try {
			SessionFactory factory = HibernateUtil.buildSessionFactory();
			
			Session session = factory.openSession();
			
			Transaction t = session.beginTransaction();
			
			Student student = new Student(request.getParameter("student"),request.getParameter("studentRoll"),request.getParameter("studentAddress"));
			
			Set<Subject> subjects = new HashSet<Subject>();
			for(int i=1;i<=Integer.parseInt(request.getParameter("noOfSubjects"));i++) {
				Subject s = new Subject(request.getParameter("subject"+i),request.getParameter("subjectCode"+i));
				subjects.add(s);
			}
			student.setSubject(subjects);
			session.save(student);
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Student with the subjects is created sucessfully ! </h3>");
			}
			
			session.close();
			
		} catch (Exception e) {
			out.println("<h3 style = 'color:red'> Error </h3>");
			e.printStackTrace();
		}
	}

}
