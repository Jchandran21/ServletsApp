package com.jayacha.Filters;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jayacha.Resources.CustomException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AnotherFilter
 */
@SuppressWarnings("serial")
//@WebServlet("/create")

public class  ValidationFilter extends HttpFilter implements Filter {

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		Logger logger = LogManager.getLogger();
		try {
			
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String uname = request.getParameter("username");
		String password = request.getParameter("password");

		if(name.length()>=3 && age >18 && uname.length()>=4 && (gender.equals("M") || gender.equals("F")) && password.length()>5) {
			chain.doFilter(request, response);
			}
		else {
			String exceptionString;
			PrintWriter out = response.getWriter();
			
			if(name.length()<3) {
				exceptionString ="Name is too short";
				out.println("Name is too short");
			}
			else if(age <18) {
				exceptionString ="Age is not appropriate";
				out.println("Age is not appropriate");

			}
			else if(uname.length()<4) {
				exceptionString ="Username is too short";
				out.println("Username is too short");

			}
			else if(password.length()<5) {
				exceptionString ="password is too short";
				out.println("password is too short");

			}
			else {
				exceptionString ="Incorrect Gender Input";
				out.println("Incorrect Gender Input");

			}
			throw new CustomException(exceptionString);
		}
		
		} catch (Exception e) {
			logger.error("Exception is thrown - "+e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			request.setAttribute("exception", e);
			rd.forward(request, response);
		}
	}

}
