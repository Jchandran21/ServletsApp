package com.jayacha.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@SuppressWarnings("serial")
@WebServlet("/update")

public class UpdateUser extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
		Logger logger = LogManager.getLogger();

		int userId = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		password = password.hashCode()+"";

		String query = "update login_details set name = ? where id = ?";

		try {

			Connection con = DbCommon.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, userId);
			int noOfRowsAffected= statement.executeUpdate();
			statement.close();
//			con.close();

			if(noOfRowsAffected <1) {
				throw new CustomException("There is no User with id - "+userId);
			}
			else {
				logger.info("User with the id - {} has been updated",userId);
			}
			res.sendRedirect("User/Update.html");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			logger.error("SQL Exception is thrown - " + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			req.setAttribute("exception", e);
			rd.forward(req, res);
		}
		catch (Exception e) {
			logger.error("An Exception is thrown - " + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			req.setAttribute("exception", e);
			rd.forward(req, res);
		}
		
	}

}
