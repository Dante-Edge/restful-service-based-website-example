package com.invisiblebridge.example.web.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessControlFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		Cookie tokenCookie = getTokenCookie(req);
		if(tokenCookie == null){
			redirectToLogin(resp);
		}
		else{
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	private void redirectToLogin(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("login.html");
	}

	private Cookie getTokenCookie(HttpServletRequest req) {
		for(Cookie cookie : req.getCookies()){
			if("token".equals(cookie.getName())){
				return cookie;
			}
		}
		return null;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
