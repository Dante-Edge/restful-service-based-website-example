package com.invisiblebridge.example.web.service;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class InboxService extends HttpServlet {
	private static final long serialVersionUID = 236721294009552861L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
	 	InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Inbox.json");
	 	IOUtils.copy(in, resp.getOutputStream());		
	}

	
}
