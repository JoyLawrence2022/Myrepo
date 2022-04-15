<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%HttpSession ses= request.getSession(); %>
<%String Airline = (String)ses.getAttribute("Airline"); %>
<%String source = (String)ses.getAttribute("source"); %>
<%String destination = (String)ses.getAttribute("destination"); %>
<%String etd = (String)ses.getAttribute("etd"); %>
<%String eta = (String)ses.getAttribute("eta"); %>
<%Float price = (Float)ses.getAttribute("total"); %>
<%ses.setAttribute("Airline", Airline); %>
<%ses.setAttribute("source", source); %>
<%ses.setAttribute("destination", destination); %>
<%ses.setAttribute("etd", etd); %>
<%ses.setAttribute("eta", eta); %>
<%ses.setAttribute("price", price); %>

<form action="BR" method="post">
Amount to be paid : <%= price %><br>
Enter your card details: <input type="text" name="card_details"/><br>
<input type="submit" value="Make payment"/>
 


</form>
</body>
</html>