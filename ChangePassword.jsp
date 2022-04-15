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
<form action="UP" method="post">
Enter current password:<input type="password" name="old_pwd"><br>
Enter new password:<input type="password" name="new_pwd"><br>
Re-enter new password:<input type="password" name="confirm_pwd"><br>
<input type="submit" value="Update Password"/>
<input type="reset" value="Reset"/>
</form>

</body>
</html>