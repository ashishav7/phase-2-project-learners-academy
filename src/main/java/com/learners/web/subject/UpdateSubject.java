package com.learners.web.subject;

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
import com.learners.util.HibernateUtil;

@WebServlet("/update-subject")
public class UpdateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateSubject() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("update-subject.html").include(request, response);
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
			String name = request.getParameter("subjectName");
			String code = request.getParameter("subjectCode");
			
			Subject subject = new Subject(id,name,code);
			
			session.update(subject);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Subject is updated sucessfully ! </h3>");
			}
			
			session.close();
			
		} catch (Exception e) {
			out.println("<h3 style = 'color:red'> Error </h3>");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
