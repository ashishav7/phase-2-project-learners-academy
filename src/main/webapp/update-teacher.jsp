<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "org.hibernate.Session,org.hibernate.SessionFactory,org.hibernate.Transaction,com.learners.util.HibernateUtil" %>
<%@ page import = "com.learners.model.Teacher,java.util.List" %>

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
		Teacher teacher = null;
		try{
			teacher = (Teacher) s.createQuery("from Teacher T WHERE T.id = " + request.getParameter("id")).list().get(0);
		}catch(IndexOutOfBoundsException e){
			out.println("<h3 style = 'color:red'> Id does not exist </h3>");
		}
%>
<% if(teacher!=null){ %>
	<form action="update-teacher" method="post">
	
		Teacher Id <input type="text" name="id" id="id" value= <%= request.getParameter("id") %> style="background: #D3D3D3;" readonly> <br/><br/>
		
		<fieldset><legend>Teacher Details</legend>
			Name <input type="text" name="teacher"  id="teacher" value="<%= teacher.getName() %>" required>	<br/>
			Rating (On a scale of 1-10)<input type="number" name="rating"  value="<%= teacher.getRating() %>" id="rating" required>	<br/>
			Specialization <input type="text" name="specialization"  id="specialization" value="<%= teacher.getSpecialization() %>" required>	<br/>
		</fieldset>
		<fieldset>
			<legend>Subject Details</legend>
			<input type="hidden" name="subjectId" value="<%= teacher.getSubject().getId() %>" id="subjectId">
			Subject Name <input type="text" name="subject" value="<%= teacher.getSubject().getName() %>" id="subject" required>	
			Subject Code <input type="text" name="subjectCode" value="<%= teacher.getSubject().getCode() %>" id="subjectCode" required>	<br/>
		</fieldset>
		<input type="submit" value="Update">
	</form>
<%} %>
</body>
</html>