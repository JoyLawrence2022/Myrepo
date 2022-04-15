package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingResult extends HttpServlet {
	PrintWriter out;
	HttpSession session;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String Airline = (String)session.getAttribute("Airline");
		String source = (String)session.getAttribute("source");
		String destination = (String)session.getAttribute("destination");
		String etd = (String)session.getAttribute("etd");
		String eta = (String)session.getAttribute("eta");
		Float price = (Float)session.getAttribute("price");
		out = response.getWriter();
		out.write("<h3>Your booking has been confirmed!</h3>");
		out.write("Booking details : " + Airline + "&emsp;from : " + source +"&emsp;to : " + destination +"&emsp;departure : " + etd +"&emsp;arriving : " + eta +"&emsp;Total amount paid = " + price + "<br>"); 
		out.write("<a href = 'Home.jsp'>Back to Home</a>");
	}
}
