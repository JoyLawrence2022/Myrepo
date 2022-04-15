<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%String airline = request.getParameter("selected_flight"); %>
<%HttpSession sess = request.getSession(); %>
<%int count = (Integer)sess.getAttribute("count"); %>

<%sess.setAttribute("airline", airline); %>
<%sess.setAttribute("count", count); %>
<%System.out.println("Run 1 From Register.jsp. Airlines selected is : "+airline); %>
<%if (airline==null) response.sendRedirect("Home.jsp"); %>
<form action="CR"  method="post">
Please fill in the registration details: <br>
Your name: <input type = "text" name = "name"/><br>
Age: <input type = "text" name = "age"/><br>
Gender: Male<input type = "radio" value="Male" name="gender" checked> &emsp; Female<input type = "radio" value="Female" name="gender"><br>
Aadhaar ID: <input type = "text" name="aadhaar"/><br>
<input type="submit" value="Register"/>
<input type="reset" value="Reset"/>
</form>
</body>
</html>