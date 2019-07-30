package com.monese.api.banking.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monese.api.banking.entity.UserTransactionHistory;

/**
 * @author C.SajjadHussain
 *
 */
@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactionHistory, BigInteger> {

	List<UserTransactionHistory> findByUserId(BigInteger userId);

}
