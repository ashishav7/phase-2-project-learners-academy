package com.learners.web.subject;

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
import com.learners.util.HibernateUtil;

@WebServlet("/list-subject")
public class ListSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListSubject() {
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
				List<Subject> subjects = session.createQuery("from Subject").list();
				
				out.print("<h1> Subject List </h1>");
				
				out.print("<style> table,td,th {"
						+ "border:2px solid red;"
						+ "padding: 10px; "
						+ "}</style>");
				out.print("<table >");
				out.print("<tr>");
					out.print("<th> Subject Id</th>");
					out.print("<th> Subject Name</th>");
					out.print("<th> Subject Code</th>");
				out.print("</tr>");
				
				for(Subject s : subjects) {
					out.print("<tr>");
					out.print("<td>"+s.getId()+"</td>");
					out.print("<td>"+s.getName()+"</td>");
					out.print("<td>"+s.getCode()+"</td>");
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
