package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminCredentials")
public class AdminCredentials extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	Statement st;
	ResultSet rs;
	ServletContext context;
	ResultSetMetaData rsmd;
	HttpSession session;
	PrintWriter out;

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			st = con.createStatement();  //creating statement object
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
		String u_id = request.getParameter("id");
		String pwd = request.getParameter("password");
		String selectQuery = "select * from AdminCredDB";

		try {
			rs = st.executeQuery(selectQuery);
			rsmd = rs.getMetaData();
			while(rs.next()) {
				if(u_id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
					System.out.println("User ID and password have matched");
					session = request.getSession();
					session.setAttribute("userid", u_id);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminActions.jsp");  //for passing to servlet, use URL pattern
					requestDispatcher.forward(request, response);
				}
				else
				{
					System.err.println("User ID and password do not match");
					out=response.getWriter();
					out.write("<h3>Admin credentials do not match!</h3><a href='AdminLogin.jsp'>Back to Login</a>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
