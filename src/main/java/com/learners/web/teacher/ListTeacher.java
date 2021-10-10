package com.learners.web.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learners.model.Subject;
import com.learners.model.Teacher;
import com.learners.util.HibernateUtil;

@WebServlet("/list-teacher")
public class ListTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListTeacher() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("home.html").include(request, response);
		
		try {
			SessionFactory factory = HibernateUtil.buildSessionFactory();
			
			Session session = factory.openSession();
			
			if(session!=null) {
				List<Teacher> teachers = session.createQuery("from Teacher").list();
				
				out.print("<h1> Teacher List </h1>");
				
				out.print("<style> table,td,th {"
						+ "border:2px solid red;"
						+ "padding: 10px; "
						+ "}</style>");
				out.print("<table >");
				out.print("<tr>");
					out.print("<th> Teacher Id</th>");
					out.print("<th> Teacher Name</th>");
					out.print("<th> Teacher Rating</th>");
					out.print("<th> Teacher Specialization</th>");
					out.print("<th> Subject Name</th>");
				out.print("</tr>");
				
				for(Teacher t : teachers) {
					out.print("<tr>");
					out.print("<td>"+t.getId()+"</td>");
					out.print("<td>"+t.getName()+"</td>");
					out.print("<td>"+t.getRating()+"</td>");
					out.print("<td>"+t.getSpecialization()+"</td>");
					out.print("<td>"+t.getSubject().getName() +"</td>");
					out.print("</tr>");
				}
				out.print("</table>");
				

			}
			
			session.close();
			
		} catch (Exception e) {
			out.println("<h3 style = 'color:red'> Error </h3>");
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
