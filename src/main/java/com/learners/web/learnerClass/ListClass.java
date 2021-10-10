package com.learners.web.learnerClass;

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

import com.learners.model.LearnerClass;
import com.learners.util.HibernateUtil;


@WebServlet("/list-class")
public class ListClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListClass() {
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
				List<LearnerClass> learnerClasses = session.createQuery("from LearnerClass").list();
				
				out.print("<h1> Classes List </h1>");
				
				out.print("<style> table,td,th {"
						+ "border:2px solid red;"
						+ "padding: 10px; "
						+ "}</style>");
				out.print("<table >");
				out.print("<tr>");
					out.print("<th> Class Id</th>");
					out.print("<th> Class Name</th>");
					out.print("<th> Smart Class </th>");
				out.print("</tr>");
				
				for(LearnerClass p : learnerClasses) {
					out.print("<tr>");
					out.print("<td>"+p.getId()+"</td>");
					out.print("<td>"+p.getName()+"</td>");
					out.print("<td>"+p.getSmartClass()+"</td>");
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
