<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	//for test purpose
	//request.getSession().setAttribute("username", null);
	request.getSession().invalidate();
	%>
	
</body>
</html>