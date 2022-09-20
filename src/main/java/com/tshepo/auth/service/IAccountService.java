package com.tshepo.auth.service;

import java.util.Optional;

import com.tshepo.auth.persistence.Account;

public interface IAccountService {
	
	void signUp(Account account);
	
	Account update(Account account);
	
	Optional<Account> findByEmail(String email);

}
