package com.tshepo.auth.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.tshepo.auth.security.AccountPrincipal;

public interface IJwtProvider {
	
	String generateJwtToken(AccountPrincipal auth);

	Authentication getAuthenticated(HttpServletRequest req);

	boolean validateToken(HttpServletRequest req);

}
