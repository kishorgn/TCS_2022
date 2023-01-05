<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>newStudent</title>
</head>
<body>
	<h2>Enter Student details</h2>
	<form:form action="student" method="post" modelAttribute="student">
		<form:label path="name">Name</form:label>
		<form:input path="name"/><br><br>
		<form:label path="email">Email Address</form:label>
		<form:input path="email"/><br><br>
		<form:label path="mobile">Mobile</form:label>
		<form:input path="mobile"/><br><br>
		<input type="submit" value="Register Student">
	</form:form>
</body>
</html>