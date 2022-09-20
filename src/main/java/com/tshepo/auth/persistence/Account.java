package com.tshepo.auth.persistence;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "account_id", unique = true, updatable = false, nullable = false)
	private String accountId;
	
	@NotBlank(message = "first Name should not be blank")
	@Size(min = 1, max = 100, message = "first name should be between 2 to 100 charaters")
	@Column(name = "first_name", updatable = true, nullable = false, length = 100)
	private String firstName;
	
	@NotBlank(message = "Last Name should not be blank")
	@Size(min = 1, max = 100, message = "Last name should be between 2 to 100 charaters")
	@Column(name = "last_name", updatable = true, nullable = false, length = 100)
	private String lastName;
	
	@NotBlank(message = "Phone should not be blank")
	@Size(min = 10, max = 12, message = "Phone should be between 10 to 12 numbers")
	@Column(name = "phone", nullable = false, updatable = true, length = 12)
	private String phone;
		
	@Email
	@Column(name = "email", unique = true, updatable = false, nullable = false)
	private String email;
	
	@NotBlank(message = "Password should not be blank")
	@Column(name = "password", updatable = true, nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	@Column(name = "last_login", updatable = false, nullable = false)
	private LocalDateTime lastLogin;
	
	@Column(name = "last_login_display", updatable = false, nullable = false)
    private LocalDateTime lastLoginDisplay;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(name = "updated_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "locked", nullable = false, updatable = true)
	private boolean isLocked = false;

	@Column(name = "active", nullable = false, updatable = true)
	private boolean isActive = false;
	
	@Transient
	private String token;

}
