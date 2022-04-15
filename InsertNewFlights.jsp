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
<form action="AFS1" method="post">

Id <input type="text" name="id" /><br/>
Name of Airlines<input type="text" name="nameOfAirlines" /><br/>
Source<input type="text" name="source" /><br/> 
Destination<input type="text" name="destination"/><br/>
ETD <input type="text" name="etd" /><br/>
ETA <input type="text" name="eta" /><br/>
Price <input type="text" name="price" /><br/>

<input type="submit" value="Submit"/>
<input type="reset" value="Reset"/>
</form>
</body>
</html>