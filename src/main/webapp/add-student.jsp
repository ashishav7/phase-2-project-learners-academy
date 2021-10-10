<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="create-student" method="post">
	<fieldset> <legend>Student</legend>
		Name <input type="text" name="student"  id="student" required>	<br/>
		Roll Number <input type="text" name="studentRoll"  id="student" required>	<br/>
		Address <input type="text" name="studentAddress"  id="student" required>	<br/>
	</fieldset>
	<% for(int i=1;i<=Integer.parseInt(request.getParameter("subject"));i++){ %>
		<fieldset> <legend>Subject<%=i %></legend>
			Name <input type="text" name="subject<%=i %>"  id="subject<%=i %>" required>	<br/><br/>
			Code <input type="text" name="subjectCode<%=i %>"  id="subjectCode<%=i %>" required>	<br/>
		</fieldset>
	<% } %>
	<input type="hidden" name="noOfSubjects" value= <%=request.getParameter("subject") %> />
		<input type="submit" value="Submit">
</form>
			
</body>
</html>