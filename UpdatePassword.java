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

public class UpdatePassword extends HttpServlet {
	String userName, password, url, driver;
	PrintWriter out;
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	ServletContext context;
	ResultSetMetaData rsmd;
	String user = "xy@gmail.com";

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			st = con.createStatement();  //creating statement object
			pst = con.prepareStatement("update AdminCredDB set password=? where username='xyz@gmail.com'");
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
		String pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		String confirm_pwd = request.getParameter("confirm_pwd");
		String selectQuery = "select password from AdminCredDB where username = 'admin'";
		out = response.getWriter();

		try {
			rs = st.executeQuery(selectQuery);
			rsmd = rs.getMetaData();
			while(rs.next()) {
				if(pwd.equals(rs.getString(1))) {
					if(new_pwd.equals(confirm_pwd)) {
						pst.setString(1, new_pwd);
						pst.executeUpdate();
						out.write("<h1>Password updated successfully!</h1><a href='AdminLogin.jsp'>Back to Login</a>");
						System.out.println("Password updated successfully");
					}
				}
				else
				{
					out.write("<h1>Password does not match!</h1><a href='AdminLogin.jsp'>Back to Login</a>");
					System.err.println("password does not match");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
