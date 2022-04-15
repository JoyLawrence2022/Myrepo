<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%HttpSession sess = request.getSession(); %>
<%String user = (String)sess.getAttribute("userid"); %>
<%if (user == null) response.sendRedirect("AdminLogin.jsp"); %>
<form action="RF" method="post">

Id <input type="text" name="id" /><br/>

<input type="submit" value="Submit"/>
<input type="reset" value="Reset"/>
</form>
</body>
</html>