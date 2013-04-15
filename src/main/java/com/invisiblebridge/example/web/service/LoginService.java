package com.invisiblebridge.example.web.service;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 2973292703834455983L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		Credential credential = getCredential(req);
		if(credential != null && "iamgod".equals(credential.getPassword())){
			String token = UUID.randomUUID().toString();
			getServletContext().setAttribute("TOKEN:" + token, token);
			sendToken(token, resp);
		}
		else{
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	private void sendToken(String token, HttpServletResponse resp) throws ServletException {
		ObjectMapper om = new ObjectMapper();
		try {
			om.writeValue(resp.getWriter(), new AuthenticationResult(token));
		} catch (Exception ex){
			throw new ServletException(ex);
		}
	}

	private Credential getCredential(HttpServletRequest req) throws ServletException {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(req.getInputStream(), Credential.class);
		} catch (Exception e){
			throw new ServletException(e);
		}		
	}
	
	
}
class Credential{
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
class AuthenticationResult{
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthenticationResult(String token) {
		super();
		this.token = token;
	}
	
}
