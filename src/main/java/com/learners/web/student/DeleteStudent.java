package com.learners.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learners.model.Student;
import com.learners.util.HibernateUtil;

@WebServlet("/delete-student")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteStudent() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("delete-student.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		
		try {
			SessionFactory factory = HibernateUtil.buildSessionFactory();
			
			Session session = factory.openSession();
			
			Transaction t = session.beginTransaction();
			
			String id = request.getParameter("id");
			
			Student student = new Student();
			student.setId(Integer.parseInt(id));
		
			session.delete(student);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Student is deleted sucessfully ! </h3>");
			}
			
			session.close();
			
		}catch(PersistenceException e) {
			out.println("<h3 style = 'color:red'>Student is assigned to a class, cannot delete the following student </h3>");
			e.printStackTrace();
		}
		catch (Exception e) {
			out.println("<h3 style = 'color:red'>Internal Error </h3>");
			e.printStackTrace();
		}	
	}

}
