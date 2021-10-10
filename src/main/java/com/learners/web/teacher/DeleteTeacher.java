package com.learners.web.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.OptimisticLockException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.learners.model.Teacher;
import com.learners.util.HibernateUtil;

@WebServlet("/delete-teacher")
public class DeleteTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteTeacher() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		request.getRequestDispatcher("delete-teacher.html").include(request, response);

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
			
			Teacher teacher = new Teacher();
			teacher.setId(Integer.parseInt(id));
		
			session.delete(teacher);
			
			t.commit();
			
			if (session != null) {
				out.print("<h3 style='color:green'> Teacher is deleted sucessfully ! </h3>");
			}
			
			session.close();
			
		}
		catch(OptimisticLockException e) {
			out.println("<h3 style = 'color:red'>Teacher id does not exist</h3>");
			e.printStackTrace();
		}
		catch (Exception e) {
			out.println("<h3 style = 'color:red'>Internal Error </h3>");
			e.printStackTrace();
		}

	}

}
