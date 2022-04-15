package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClientRegistration extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	PreparedStatement pst;
	ServletContext context;
	HttpSession session;
	PrintWriter out;

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			//creating the statement object
			pst=con.prepareStatement("insert into UserDetailsDB values (?, ?, ?, ?)");
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
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String aadhaar = request.getParameter("aadhaar");
		session = request.getSession();
		String airline = (String)session.getAttribute("airline");
		int count = (Integer)session.getAttribute("count");
		session.setAttribute("airline", airline);
		session.setAttribute("count", count);
		
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setString(3, gender);
			pst.setString(4, aadhaar);
			//to send the values to DB
			pst.executeUpdate();
			System.out.println("Record inserted into UserDetailsDB");
			System.out.println("Value of airline in Client Registration is : "+airline);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SF");
			requestDispatcher.forward(request, response);
		} catch(NullPointerException e) {
			e.printStackTrace();
			out.write("<h3>Please enter proper values!</h3><a href='Home.jsp'>Back to Home</a>");
		} catch(Exception e) {
			e.printStackTrace();
			out.write("<h3>Please enter proper values!</h3><a href='Home.jsp'>Back to Home</a>");
		}
		
	}
}