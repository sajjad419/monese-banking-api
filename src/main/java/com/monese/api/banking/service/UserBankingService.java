package com.monese.api.banking.service;

import java.math.BigInteger;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monese.api.banking.entity.User;
import com.monese.api.banking.entity.UserTransactionHistory;
import com.monese.api.banking.pojo.ResponseList;
import com.monese.api.banking.pojo.TransferMoneyRequest;
import com.monese.api.banking.repository.UserRepository;
import com.monese.api.banking.repository.UserTransactionRepository;

/**
 * @author C.SajjadHussain
 *
 */

@Service
public class UserBankingService {
	@Autowired
	private UserRepository userRepository;
	private UserTransactionRepository userTransactionRepository;

	@Autowired
	public UserBankingService(UserTransactionRepository userTransactionRepository) {
		this.userTransactionRepository = userTransactionRepository;
	}

	public ResponseList fetchUserTranscationHistory(String accountNumber) {

		if (accountNumber == null || accountNumber == "") {
			throw new ValidationException("Invalid User Id");
		}
		User user = userRepository.findByAccountNumber(accountNumber);
		if (user == null) {
			throw new ValidationException("Invalid User Id");
		}
		System.out.println("accountNumber::" + accountNumber + ":" + user.getId());
		ResponseList response = new ResponseList();
		List<UserTransactionHistory> entityList = userTransactionRepository.findByUserId(user.getId());
		System.out.println("entityList::" + entityList.size());
		if (entityList != null) {
			response.setItems(entityList);
		}

		response.setUser(user);
		response.setTotal(entityList != null ? entityList.size() : 0);

		return response;
	}

	public String transferMoney(TransferMoneyRequest transferMoneyRequest) {
		String transferStatus = "Transaction Failed";
		String fromAccountNumber = transferMoneyRequest.getFromAccountNumber();
		String toAccountNumber = transferMoneyRequest.getToAccountNumber();
		Double transferAmount = transferMoneyRequest.getTransferAmount();
		if (fromAccountNumber == null || fromAccountNumber == "") {
			throw new ValidationException("Invalid Request");
		}
		if (toAccountNumber == null || toAccountNumber == "") {
			throw new ValidationException("Invalid Request");
		}

		User fromUser = userRepository.findByAccountNumber(fromAccountNumber);

		if (fromUser == null) {
			throw new ValidationException("Invalid Request");
		}
		User toUser = userRepository.findByAccountNumber(toAccountNumber);
		if (toUser == null) {
			throw new ValidationException("Invalid To Account Number");
		}

		boolean withDrawStatus = withDrawMoney(fromUser, transferAmount);

		boolean depositStatus = depositMoney(toUser, transferAmount);

		if (withDrawStatus && depositStatus) {
			UserTransactionHistory fromUserTransactionHistory = saveUserTransactionHistory(fromUser.getId(),
					fromUser.getCurrentBalance(), "Transfer", toUser.getAccountNumber(), transferAmount);

			UserTransactionHistory toUserTransactionHistory = saveUserTransactionHistory(toUser.getId(),
					toUser.getCurrentBalance(), "Deposit", fromUser.getAccountNumber(), transferAmount);
			userRepository.saveAndFlush(fromUser);
			userRepository.saveAndFlush(toUser);
			transferStatus = "Transaction Successfull Transaction ID:" + fromUserTransactionHistory.getTransactionId();
		}
		return transferStatus;
	}

	private boolean withDrawMoney(User user, Double amount) {
		boolean withDrawStatus = false;
		if (amount == null || amount <= 0.0) {
			throw new ValidationException("Transfer Amount Should be greater than 0");
		}
		Double currentBalance = user.getCurrentBalance();
		if (user != null) {
			if (amount > currentBalance) {
				throw new ValidationException("Insufficient balance to Transfer");
			}
			user.setCurrentBalance(currentBalance - amount);
			withDrawStatus = true;
		}

		return withDrawStatus;

	}

	private boolean depositMoney(User user, Double amount) {
		boolean depositStatus = false;
		Double currentBalance = user.getCurrentBalance();
		user.setCurrentBalance(currentBalance + amount);
		depositStatus = true;

		return depositStatus;

	}

	private UserTransactionHistory saveUserTransactionHistory(BigInteger userId, Double currentBalance,
			String transactionType, String referenceAccount, Double transactionAmount) {
		UserTransactionHistory userTransactionHistory = new UserTransactionHistory();
		userTransactionHistory.setUserId(userId);
		userTransactionHistory.setReferenceAccount(referenceAccount);
		userTransactionHistory.setTransactionType(transactionType);
		userTransactionHistory.setTransactionAmount(transactionAmount);
		userTransactionHistory.setCurrentBalance(currentBalance);
		userTransactionHistory.setTransactionStatus("Success");
		userTransactionRepository.saveAndFlush(userTransactionHistory);

		return userTransactionHistory;
	}

}
