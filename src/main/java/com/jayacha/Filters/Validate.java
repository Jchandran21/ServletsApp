package com.jayacha.Filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
@WebServlet("/validate")

	public class Validate extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException, ServletException {
		Logger logger = LogManager.getLogger();
		HttpSession session = req.getSession();

		String userName = req.getParameter("username");
		String password = req.getParameter("password").hashCode() +"";
		
		String query = "select password from login_details where username = ? " ;
		Connection con;
		try {
			con = DbCommon.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, userName);

			ResultSet set = statement.executeQuery();
			set.next();
			
			if(set.getString("password").equals(password)){
				logger.info("The User {} has logged-in",userName);
				session.setAttribute("username", userName);
				session.setAttribute("password", password);
				res.sendRedirect("User/Home.html");
			}
			else {
				logger.info("Wrong Password has been entered by user");
				RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
				Exception e = new CustomException("Password is Wrong");
				req.setAttribute("exception", e);
				rd.forward(req, res);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("SQL Exception is thrown - "+e.getMessage());
			Exception se = new CustomException("No such username exists");
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			req.setAttribute("exception", se);
			rd.forward(req, res);
		}	
	}
}
