package com.tshepo.auth.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.auth.persistence.Account;

@Repository
@Transactional(readOnly = true)
public interface IAccountRepository extends CrudRepository<Account, Long>{
	
	Optional<Account> findByEmail(String email);

}
