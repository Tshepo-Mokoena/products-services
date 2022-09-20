package com.tshepo.auth.service.implementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tshepo.auth.persistence.Account;
import com.tshepo.auth.persistence.Role;
import com.tshepo.auth.persistence.repository.IAccountRepository;
import com.tshepo.auth.service.IAccountService;
import com.tshepo.auth.util.SecurityUtil;

@Service
public class AccountService implements IAccountService{
	
	private IAccountRepository accountRepository;
	
	@Autowired
	private void setAccountService(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void signUp(Account account) {
		account.setAccountId(UUID.randomUUID().toString());
		account.setUpdatedAt(LocalDateTime.now());
		account.setCreatedAt(LocalDateTime.now());
		account.setRole(Role.USER);
		account.setPassword(SecurityUtil.passwordEncoder().encode(account.getPassword()));
		accountRepository.save(account);
	}

	@Override
	public Account update(Account account) {
		account.setUpdatedAt(LocalDateTime.now());
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

}
