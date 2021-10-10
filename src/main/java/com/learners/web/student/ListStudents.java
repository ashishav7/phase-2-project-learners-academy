package com.learners.web.student;

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

import com.learners.model.Student;
import com.learners.util.HibernateUtil;

@WebServlet("/list-students")
public class ListStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListStudents() {
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
				List<Student> students = session.createQuery("from Student").list();
				
				out.print("<h1> Student List </h1>");
				
				out.print("<style> table,td,th {"
						+ "border:2px solid red;"
						+ "padding: 10px; "
						+ "}</style>");
				out.print("<table >");
				out.print("<tr>");
					out.print("<th> Student Id</th>");
					out.print("<th> Student Name</th>");
					out.print("<th> Student Roll Number </th>");
					out.print("<th> Student Address </th>");
				out.print("</tr>");
				
				for(Student s : students) {
					out.print("<tr>");
					out.print("<td>"+s.getId()+"</td>");
					out.print("<td>"+s.getName()+"</td>");
					out.print("<td>"+s.getRollNo()+"</td>");
					out.print("<td>"+s.getAddress()+"</td>");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
