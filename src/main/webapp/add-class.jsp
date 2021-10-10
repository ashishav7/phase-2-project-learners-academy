<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<form action="add-class" method="post">
				Class Name <input type="text" name="className" id = "className" required/>
				<label for="smartClass">Smart Class</label>
				<select id="smartClass" name="smartClass">
				 	<option value="Yes">Yes</option>
				  	<option value="No">No</option>
				</select> 
				<% for(int i=1;i<=Integer.parseInt(request.getParameter("students"));i++){ %>
					<fieldset> <legend>Student<%=i %></legend>
					 Name <input type="text" name="student<%=i %>"  id="student<%=i %>" required>	<br/>
					 Roll Number <input type="text" name="studentRoll<%=i %>"  id="student<%=i %>" required>	<br/>
					 Address <input type="text" name="studentAddress<%=i %>"  id="student<%=i %>" required>	<br/>
					</fieldset>
				<% } %>
				
				<% for(int i=1;i<=Integer.parseInt(request.getParameter("subjects"));i++){ %>
					<fieldset> <legend>Subject<%=i %></legend>
					 Name <input type="text" name="subject<%=i %>"  id="subject<%=i %>" required>	<br/>
					 Subject Code <input type="text" name="subjectCode<%=i %>"  id="subjectCode<%=i %>" required>	<br/>
					</fieldset>
				<% } %>
				<input type="hidden" name="noOfStudents" value= <%=request.getParameter("students") %> required/>
				<input type="hidden" name="noOfSubjects" value= <%=request.getParameter("subjects") %> required/>
				<input type="submit" value="Submit">
			</form>
	
</body>
</html>