<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "org.hibernate.Session,org.hibernate.SessionFactory,org.hibernate.Transaction,com.learners.util.HibernateUtil" %>
<%@ page import = "com.learners.model.Subject,java.util.List" %>

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
		Subject subject = null;
		try{
			subject = (Subject) s.createQuery("from Subject S WHERE S.id = " + request.getParameter("id")).list().get(0);
		}catch(IndexOutOfBoundsException e){
			out.println("<h3 style = 'color:red'> Id does not exist </h3>");
		}
%>

<% if(subject!=null){ %>
	<form action="update-subject" method="post">
		Subject Id <input type="text" name="id" id="id" value= <%= request.getParameter("id") %> style="background: #D3D3D3;" readonly> <br/><br/>
		Subject Name <input type="text" name="subjectName" id="subjectName" value="<%= subject.getName() %>" required>
		Subject Code <input type="text" name="subjectCode" id="subjectCode" value="<%= subject.getCode() %>" required>
		
		<input type="submit" value="Submit">
	</form> 
	<%	} %>
	


</body>
</html>