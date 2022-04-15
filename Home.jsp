<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div style="float:right">
  <a href=AdminLogin.jsp>Admin login</a>
</div>
<h1>Welcome to Fly Away!</h1>
<form action="FF" method="post">
Date of travel: &nbsp;<input type="date" value="2022-04-14"><br/>
Source: &nbsp;<input type="text" name="source" /><br/>
Destination: &nbsp;<input type="text" name="destination"/><br/>
No. of persons: &nbsp;<input type="text" name="noOfPersons"/><br/>
<input type="submit" value="Search"/>
<input type="reset" value="Reset"/>
</form>
</body>
</html>