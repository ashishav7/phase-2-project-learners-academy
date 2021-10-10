package com.learners.web.subject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learners.model.Subject;
import com.learners.util.HibernateUtil;

@WebServlet("/delete-subject")
public class DeleteSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteSubject() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("delete-subject.html").include(request, response);
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
			
			Subject subject = new Subject();
			subject.setId(Integer.parseInt(id));
		
			session.delete(subject);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Subject is deleted sucessfully ! </h3>");
			}
			
			session.close();
			
		}catch(PersistenceException e) {
			out.println("<h3 style = 'color:red'>Subject is either assigned to a class or a student or is taken by a teacher, cannot delete the following subject </h3>");
			e.printStackTrace();
		}
		catch (Exception e) {
			out.println("<h3 style = 'color:red'>Internal Error </h3>");
			e.printStackTrace();
		}
	}

}
