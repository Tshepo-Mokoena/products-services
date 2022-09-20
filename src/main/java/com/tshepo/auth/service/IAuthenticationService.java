package com.tshepo.auth.service;

import com.tshepo.auth.persistence.Account;

public interface IAuthenticationService {
	
	Account signInAndReturnJwt(Account account);
}
