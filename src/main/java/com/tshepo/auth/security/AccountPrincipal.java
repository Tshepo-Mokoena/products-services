package com.tshepo.auth.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tshepo.auth.persistence.Account;

import lombok.Builder;
import lombok.Getter;

@Builder
@SuppressWarnings("serial")
@Getter
public class AccountPrincipal implements UserDetails{
		
	private Long id;
	
	private String accountId;
	
	private String email;
	
	transient  private String password;
		
	private Set<GrantedAuthority> authorities;
	
	transient  private Account account;
		
	@Autowired
	private void setAccountPrincipal(Account account, Set<GrantedAuthority> authorities) {
		this.id = account.getId();
		this.account = account;
		this.email = account.getEmail();
		this.password = account.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !account.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return account.isActive();
	}

}
