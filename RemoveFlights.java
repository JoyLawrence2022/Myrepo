package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFlights extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	ResultSetMetaData rsmd;
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
			pst=con.prepareStatement("delete from FlyAwayDB where id = ?");
			st=con.createStatement();
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
		String selectQuery = "select id from FlyAwayDB";
		out = response.getWriter();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			rs = st.executeQuery(selectQuery);
			rsmd = rs.getMetaData();
			boolean found = false;
			while (rs.next()) {
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					if(id == Integer.parseInt(rs.getString(i))){
						pst.setInt(1, id);
						pst.executeUpdate();
						out.write("<h3>Flight Record deleted successfully!</h3><a href='DeleteFlights.jsp'>Back to previous page</a>");
						System.out.println("Record deleted");
						found = true;
						break;
					}
				}
			}
			if (found == false) {
				out.write("<h3>ID entered is not a valid ID!</h3><a href='DeleteFlights.jsp'>Back to previous page</a>");
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
			out.write("<h3>Please enter proper ID/ do not submit blank entry!</h3><a href='DeleteFlights.jsp'>Back to previous page</a>");
		} catch(Exception e) {
			e.printStackTrace();
			out.write("<h3>Please enter proper ID/ do not submit blank entry!</h3><a href='DeleteFlights.jsp'>Back to previous page</a>");
		}
	}
}
