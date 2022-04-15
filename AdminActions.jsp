<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>.button1 {width: 250px;} </style>
<title>Insert title here</title>
</head>
<body>
<%HttpSession sess = request.getSession(); %>
<%String user = (String)sess.getAttribute("userid"); %>
<%if (user == null) response.sendRedirect("AdminLogin.jsp"); %>
<h3>Admin actions:</h3><br>
<table>
<tr><th>Actions</th></tr>
<tr><td><button class="button button1" onclick="window.location.href='ChangePassword.jsp'">Change password</button></td></tr>
<tr><td><button class="button button1" onclick="window.location.href='SD'">Show list : Source and Destination</button></td></tr>
<tr><td><button class="button button1" onclick="window.location.href='LOF'">Show List of Flights</button></td></tr>
<tr><td><button class="button button1" onclick="window.location.href='InsertNewFlights.jsp'">Add Flight</button></td></tr>
<tr><td><button class="button button1" onclick="window.location.href='DeleteFlights.jsp'">Delete Flights</button></td></tr>

</table>
</body>
</html>