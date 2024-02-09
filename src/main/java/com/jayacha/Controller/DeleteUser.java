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
@WebServlet("/delete")

public class DeleteUser extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		Logger logger = LogManager.getLogger();
		HttpSession session = req.getSession();
		int userId = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");
		password = password.hashCode()+"";
		String username = (String) req.getAttribute("username");
		String query  = "delete from login_details where id=?;";

		try {
			Connection con = DbCommon.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, userId);
			int noOfRowsAffected= statement.executeUpdate();
			statement.close();
			
			if(noOfRowsAffected <1) {
				throw new CustomException("There is no User with id - " + userId);
			}
			else if(session.getAttribute("username").equals(username)) {
				RequestDispatcher rd = req.getRequestDispatcher("logout");
				User.numberOfUsers--;
				logger.info("User with the id - {} has been deleted",userId);
				rd.forward(req, res);
			}
			else {
				User.numberOfUsers--;
				logger.info("User with the id - {} has been deleted",userId);
				res.sendRedirect("User/Delete.html");
			}

		} catch (SQLException | ClassNotFoundException e) {
			logger.error("SQL Exception is thrown / " + e.getMessage());
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
