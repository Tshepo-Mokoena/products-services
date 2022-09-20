package com.tshepo.auth.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.tshepo.auth.persistence.Account;
import com.tshepo.auth.security.jwt.IJwtProvider;
import com.tshepo.auth.service.IAuthenticationService;


@Service
public class AuthenticationService implements IAuthenticationService{
	
	private AuthenticationManager authenticationManager;
		
	private IJwtProvider jwtProvider;
	
	@Autowired
	private void setAuthenticationService(IJwtProvider jwtProvider, AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public Account signInAndReturnJwt(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
