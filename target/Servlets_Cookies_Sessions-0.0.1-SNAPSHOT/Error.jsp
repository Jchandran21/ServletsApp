<%@page import="com.jayacha.Model.User"%>
<%@page import="com.jayacha.Resources.CustomException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Failed</title>
</head>
<body bgcolor = "red">
		<%
		 CustomException e = (CustomException)request.getAttribute("exception");
		%>   
		
		<h1><%
		out.println("Error information"); 		
		%></h1>		
		 
        <h3>
        <% out.println("Reason: " + e.getMessage());%>
        </h3>
       
		 <h3>
        <% if(e.getUser() != null){
        	User user = e.getUser();
        	out.println("User data - " + " - " + user.getName() +" - " + user.getAge() +" - " + user.getGender() +" - " + user.getUsername());
        	}
        		
        %>
        </h3>
</body>