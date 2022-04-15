package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFlightServlet extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	PreparedStatement pst;
	ServletContext context;
	PrintWriter out;

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			//creating the statement object
			pst=con.prepareStatement("insert into FlyAwayDB values (?, ?, ?, ?, ?, ?, ?)");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(con + " Connection established from Servlet");
		out = response.getWriter();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String nameOfAirline = request.getParameter("nameOfAirlines");
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			String etd = request.getParameter("etd");
			String eta = request.getParameter("eta");
			float price = Float.parseFloat(request.getParameter("price"));

			pst.setInt(1, id);
			pst.setString(2, nameOfAirline);
			pst.setString(3, source);
			pst.setString(4, destination);
			pst.setString(5, etd);
			pst.setString(6, eta);
			pst.setFloat(7, price);

			//to send the values to DB
			pst.executeUpdate();
			System.out.println("Record inserted");
		}   catch(java.sql.SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			out.write("<h1>ID field should be unique.</h1><a href='InsertNewFlights.jsp'>Back to entry page</a>");
		}	catch(NumberFormatException e) {
			e.printStackTrace();
			out.write("<h1>Please enter proper values. Do not leave any blank input!</h1><a href='InsertNewFlights.jsp'>Back to entry page</a>");
		}	catch(Exception e) {
			e.printStackTrace();
			out.write("<h1>Please enter proper values. Do not leave any blank input!</h1><a href='InsertNewFlights.jsp'>Back to entry page</a>");
		}
	}
}
