package com.jayacha.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jayacha.Model.User;
import com.jayacha.Resources.CustomException;
import com.jayacha.Resources.DbCommon;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/create")
public class CreateUser extends HttpServlet {
	@Override
	

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException, ServletException {
			
		Logger logger = LogManager.getLogger();
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		String uname = req.getParameter("username");
		String password = req.getParameter("password");
		password = password.hashCode()+"";
		String query  = "insert into login_details(name,age,gender,username,password) values(?,?,?,?,?);";
		try {
			Connection con = DbCommon.getConnection();
			PreparedStatement statement = con.prepareStatement(query);

			statement.setString(1, name);
			statement.setInt(2, age);
			statement.setString(3, gender);
			statement.setString(4, uname);
			statement.setString(5, password);

			System.out.println(statement.toString());
			User user = new User(name, age, gender, uname,password);	
			if(User.numberOfUsers>=2) {
				throw new CustomException(user,"Total Number of Users is reached");
			}
			else {			
				User.numberOfUsers++;
			}
			statement.execute();
			statement.close();
//			con.close();

			logger.info("New Account for {} is created",name);
			if(session.getAttribute("username")==null) {
				session.setAttribute("password", password);
				session.setAttribute("username", uname);
			}
			res.sendRedirect("User/Home.html");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("SQL Exception is thrown - "+e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			req.setAttribute("exception", e);
			rd.forward(req, res);
		} catch (Exception e) {
			logger.error("An Exception is thrown - " + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			req.setAttribute("exception", e);
			rd.forward(req, res);
		}
	}
}
