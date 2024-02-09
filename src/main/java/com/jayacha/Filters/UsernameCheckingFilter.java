package com.jayacha.Filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jayacha.Resources.DbCommon;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class DummyFIlter
 */
@SuppressWarnings("serial")
public class UsernameCheckingFilter extends HttpFilter implements Filter {
       
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		Logger logger = LogManager.getLogger();
		int userId = Integer.parseInt(request.getParameter("id"));
		String query = "select username from login_details where id = ?";
		try {
			Connection con = DbCommon.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet set = statement.executeQuery();
			set.next();
			request.setAttribute("username", set.getString("username"));
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("SQL Exception is thrown / " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			request.setAttribute("exception", e);
			rd.forward(request, response);
		}
		chain.doFilter(request, response);
	}

}
