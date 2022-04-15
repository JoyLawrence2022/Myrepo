package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceDestination extends HttpServlet {
	String userName,password,url,driver;
	Connection con;
	Statement st;
	ServletContext context;
	public void init(ServletConfig config) throws ServletException {
		context=config.getServletContext();
		try {
			//loading the driver
			Class.forName(context.getInitParameter("driver"));
			//creating the connection 
			con=DriverManager.getConnection(context.getInitParameter("url"), 
			context.getInitParameter("userName"),context.getInitParameter("password"));
			st=con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//getting data
	public void getRecords(HttpServletResponse resp)throws Exception { 
		PrintWriter out=resp.getWriter();
		String selectQuery="select * from FlyAwayDB order by id";
		//it is pointer to table(data)
		ResultSet rs=st.executeQuery(selectQuery);
		ResultSetMetaData rsmd=rs.getMetaData();
		out.write("<html><head><style>table, th, td {border: 1px solid black;}</style></head>" + 
				"<body><table><br><tr><th>Source</th><th>Destination</th></tr><br>");
		while(rs.next()) {
			out.write("<tr>");
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				if(i==3) {
					out.write("<td>"+rs.getString(3)+"</td>");
				}
				if(i==4) {
					out.write("<td>"+rs.getString(4)+"</td>");
				}
			}
			out.write("</tr><br>");
		}
		out.write("</table><br></body></html>");
		out.write("<a href='AdminActions.jsp'>Back to Admin Control Page</a>");
		out.write("</body></html>");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			getRecords(resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
