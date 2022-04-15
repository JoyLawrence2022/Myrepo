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
import javax.servlet.http.HttpSession;

public class SelectedFlight extends HttpServlet {
	String userName, password, url, driver;
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	ResultSetMetaData rsmd;
	HttpSession session;
	ServletContext context;
	int price, i;
	String source, destination, etd, eta;

	public void init(ServletConfig config) throws ServletException{
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection
			con=DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("userName"), context.getInitParameter("password"));
			//creating the statement object
			pst=con.prepareStatement("select airlines, source, destination, etd, eta, price from FlyAwayDB where airlines = ?");
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
		System.out.println(con + " Connection established from SelectedFlight.java");
		PrintWriter out=response.getWriter();
		session = request.getSession();
		String airline = (String) session.getAttribute("airline");
		//String airline = (String) request.getAttribute("airline");
		//if(airline == null) {
		//	response.sendRedirect("Home.jsp");
		//}
		//else {
			System.out.println("Run 3..Value of airline in SelectedFlight is = " +airline);
			try {
				pst.setString(1, airline);
				rs = pst.executeQuery();
				System.out.println("Execute query in progress...");
				rsmd = rs.getMetaData();
				out.write("<h3>You have selected the following flight!</h3>");
				out.write("<table><br><tr><th>Airlines</th><th>Source</th><th>Destination</th><th>ETD</th><th>ETA</th><th>Price</th></tr><br><tr>");
				while(rs.next()) {
					for(i=1;i<=rsmd.getColumnCount();i++) {
						
							out.write("<td>"+rs.getString(i)+"</td>");
							System.out.println("Inside for loop...");
					}
					out.write("</tr></table>");
					source = rs.getString(2);
					destination = rs.getString(3);
					etd = rs.getString(4);
					eta = rs.getString(5);
					price = Integer.parseInt(rs.getString(6));
					//System.out.println("This is from SelectedFlight.java" +source + destination + etd + eta);
				}
				out.write("<br><a href=PaymentGateway.jsp>Proceed with booking</a>");
				
				int count = (Integer)session.getAttribute("count");
				float total = price * count;
				
				session.setAttribute("Airline", airline);
				session.setAttribute("source", source);
				session.setAttribute("destination", destination);
				session.setAttribute("etd", etd);
				session.setAttribute("eta", eta);
				session.setAttribute("total",total);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//	}
}
