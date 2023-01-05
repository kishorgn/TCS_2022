<%@page import="com.tcs.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student</title>
</head>
<body>
	<%
	if(request.getParameter("msg")!=null){
		%>
		<%= request.getParameter("msg") %>
		<%
	}
	%>
	<h2>Student details</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Edit / Delete</th>
		</tr>
	
	<%
	List<Student> students = (List<Student>) request.getAttribute("students");
	for(Student student : students){
		%>
		<tr>
			<td><%= student.getId() %></td>
			<td><%= student.getName() %></td>
			<td><%= student.getEmail() %></td>
			<td><%= student.getMobile() %></td>
			<td><a href="editStudent/<%=student.getId()%>">Edit</a> | <a href="#">Delete</a></td>
		</tr>
		<%
	}
	%>
	</table>
	<br><br>
	<a href="newStudent">Add new student</a>
</body>
</html>