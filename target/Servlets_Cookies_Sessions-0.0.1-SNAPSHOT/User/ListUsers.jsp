<%@page import="jakarta.servlet.annotation.WebServlet"%>
<%@page import="com.jayacha.Resources.DbCommon"%>
<%@page import="java.sql.*"%>
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users List</title>
</head>
<body>
<%
		String query = "select * from login_details;";
		Connection con = DbCommon.getConnection();
		Statement statement = con.createStatement();
		ResultSet set = statement.executeQuery(query);
%>
<h1>
<%
		while(set.next()){
			out.println(set.getInt("id") + "  -  " + set.getString("name") + "  -  " + set.getInt("age")+ "  -  " + set.getString("gender")+ "  -  " + set.getString("username")+ "  -  " + set.getString("password")); 
			out.println("<br>");
		}
		statement.close();


%>
</h1>

<br><br><br>
 	 	<a href= "Home.html"> Go Back</a>
</body>
</html>