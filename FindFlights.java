package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FindFlights extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	PreparedStatement pst;
	ServletContext context;
	HttpSession session;

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			//creating the statement object
			pst=con.prepareStatement("select airlines, source, destination, etd, eta, price from FlyAwayDB where source = ? and destination = ?");
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

		PrintWriter out = response.getWriter();
		System.out.println(con + " Connection established from Servlet");
		session = request.getSession();
		try {
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			int numberOfPersons = Integer.parseInt(request.getParameter("noOfPersons"));
			session.setAttribute("count", numberOfPersons);

			pst.setString(1, source);
			pst.setString(2, destination);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			if(rs.next()) {
				out.write("<h3>The following flights are available:</h3>");
				out.write("<table><tr><th>Airlines</th><th>Source</th><th>Destination</th><th>ETD</th><th>ETA</th><th>Price</th><th>Select Flight</th></tr><br><tr>");
				String s1="<form action='Register.jsp' method = 'post'>";
				out.write(s1);
				do {
					for(int i=1;i<=rsmd.getColumnCount();i++) 
					{
						out.write("<td>"+rs.getString(i)+"</td>");
					}
					out.write("<td><input type='radio' value='" + rs.getString(1) + "' name= 'selected_flight'></td>");
					out.write("</tr><br>");
				}
				while(rs.next());
				out.write("</table><input type='submit' value='Submit' >");
				out.write("</form>");
				//RequestDispatcher requestDispatcher = request.getRequestDispatcher("Register.jsp");
				//requestDispatcher.forward(request, response);
			}	
			else {
				out.write("<h3>No flights are available as per your search criteria!</h3><a href='Home.jsp'>Back to Home</a>");
				System.out.println("No flights available");
			}
		} catch(Exception e) {
			e.printStackTrace();
			out.write("<h3>Please enter proper values. Do not leave any blank input!</h3><a href='Home.jsp'>Back to Home</a>");
		}
	}
}

