package com.jayacha.Filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AuthenticationFilter extends HttpFilter implements Filter {

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("password") != null) {
			chain.doFilter(request, response);
		}
		else {
			response.sendRedirect("../Index.html");
		}
		
	}
}
