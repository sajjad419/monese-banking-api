package com.monese.api.banking.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.monese.api.banking.entity.User;

/**
 * @author C.SajjadHussain
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>, JpaSpecificationExecutor<User> {

	public User findByAccountNumber(String accountNumber);

}
