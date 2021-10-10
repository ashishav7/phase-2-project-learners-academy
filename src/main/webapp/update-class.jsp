<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "org.hibernate.Session,org.hibernate.SessionFactory,org.hibernate.Transaction,com.learners.util.HibernateUtil" %>
<%@ page import = "com.learners.model.LearnerClass,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 
		SessionFactory factory = HibernateUtil.buildSessionFactory();
	
		Session s = factory.openSession();
		LearnerClass learnerClass = null;
		try{
			learnerClass = (LearnerClass) s.createQuery("from LearnerClass L WHERE L.id = " + request.getParameter("id")).list().get(0);
		}catch(IndexOutOfBoundsException e){
			out.println("<h3 style = 'color:red'> Id does not exist </h3>");
		}
	%>
	
	<% if(learnerClass!=null){ %>
	<form action="update-class" method="post">
		Class Id <input type="text" name="id" id="id" value= <%= request.getParameter("id") %> style="background: #D3D3D3;" readonly> <br/><br/>
		Class Name <input type="text" name="class_name" id="class_name" value="<%= learnerClass.getName() %>" required> 
		<select id="smartClass" name="smartClass">
				 <option value="Yes">Yes</option>
				 <option value="No">No</option>
			</select> 
		<input type="submit" value="Submit">
	</form>
	<%	} %>
</body>
</html>