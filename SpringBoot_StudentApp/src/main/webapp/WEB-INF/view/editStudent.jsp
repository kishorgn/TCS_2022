<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editStudent</title>
</head>
<body>
	<h2>Update Student details</h2>
	<form:form action="${pageContext.request.contextPath}/updateStudent" method="post" modelAttribute="student">
		<form:label path="id">ID</form:label>
		<form:input path="id" readonly=""/><br><br>
		<form:label path="name">Name</form:label>
		<form:input path="name"/><br><br>
		<form:label path="email">Email</form:label>
		<form:input path="email"/><br><br>
		<form:label path="mobile">Mobile</form:label>
		<form:input path="mobile"/><br><br>
		<input type="submit" value="Update">
	</form:form>
	
</body>
</html>