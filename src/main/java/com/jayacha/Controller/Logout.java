//$Id$
package com.jayacha.Controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class Logout extends HttpServlet{
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException, ServletException {
		Logger logger = LogManager.getLogger();

		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("username");
		logger.info("User - {} has logged out",userName);	
		
		session.removeAttribute("username");
		session.removeAttribute("password");
		session.invalidate();
		
		res.sendRedirect("Index.html");
	}
}
