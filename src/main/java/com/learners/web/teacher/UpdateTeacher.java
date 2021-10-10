package com.learners.web.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learners.model.Subject;
import com.learners.model.Teacher;
import com.learners.util.HibernateUtil;

@WebServlet("/update-teacher")
public class UpdateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateTeacher() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("update-teacher.html").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		
		try {
			SessionFactory factory = HibernateUtil.buildSessionFactory();
			
			Session session = factory.openSession();
			
			Transaction t = session.beginTransaction();
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("teacher");
			int rating = Integer.parseInt(request.getParameter("rating"));
			String specialization = request.getParameter("specialization");
			
			
			Teacher teacher = new Teacher(id,name,rating,specialization);
			Subject subject = new Subject(Integer.parseInt(request.getParameter("subjectId")),request.getParameter("subject"),request.getParameter("subjectCode"));
			teacher.setSubject(subject);
			session.update(teacher);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Teacher is updated sucessfully ! </h3>");
			}
			
			session.close();
			
		} catch (Exception e) {
			out.println("<h3 style = 'color:red'> Error </h3>");
			e.printStackTrace();
		}
	}

}
