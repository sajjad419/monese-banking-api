package com.monese.api.banking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monese.api.banking.exception.ValidationException;
import com.monese.api.banking.pojo.ErrorResponse;
import com.monese.api.banking.pojo.ResponseList;
import com.monese.api.banking.pojo.TransferMoneyRequest;
import com.monese.api.banking.service.UserBankingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author C.SajjadHussain
 *
 */
@RestController
public class UserBankingController {

	private UserBankingService service;

	@Autowired
	public UserBankingController(UserBankingService service) {
		this.service = service;
	}

	@ApiOperation(value = "/v1/api/users/1234567890123456/history/transaction")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{version}/api/users/{accountNumber}/history/transaction")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = ResponseList.class),
			@ApiResponse(code = 400, message = "Invalid Request Data or Input", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Authentication Failure, Valid Credentials Required To Access Resource", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Authorization Failure, Privileges Required To Access Protected Resource", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Mal-function", response = ErrorResponse.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = ErrorResponse.class) })
	public @ResponseBody ResponseList fetchUserTranscationHistory(
			@RequestHeader(value = "access_token") @Valid String accessToken,
			@PathVariable(value = "version", required = true) @Valid String apiVersion,
			@PathVariable(value = "accountNumber", required = true) @Valid String accountNumber)
			throws ValidationException {

		String sampleAccessToken = "ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe";// In real time
																										// this will be
																										// Validated
																										// this using
																										// Authentication
																										// Module

		if (accessToken == null || !accessToken.equals(sampleAccessToken)) {
			throw new ValidationException("Invalid Access or Session Token");
		} else {
			return service.fetchUserTranscationHistory(accountNumber);
		}

	}

	@ApiOperation(value = "/v1/api/users/transfermoney")
	@PostMapping(value = "/{version}/api/users/transfermoney")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Created", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Request Data or Input", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Authentication Failure, Valid Credentials Required To Access Resource", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Authorization Failure, Privileges Required To Access Protected Resource", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Mal-function", response = ErrorResponse.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = ErrorResponse.class) })
	public @ResponseBody String transferMoney(@RequestHeader(value = "access_token") @Valid String accessToken,
			@PathVariable(value = "version", required = true) @Valid String apiVersion,
			@RequestBody @Valid TransferMoneyRequest transferMoneyRequest)
	// @PathVariable(value = "accountNumber", required = true) @Valid String
	// accountNumber,
//			@PathVariable(value = "toAccountNumber", required = true) @Valid String toAccountNumber,
//			@PathVariable(value = "transferAmount", required = true) @Valid Double transferAmount)
			throws ValidationException {

		String sampleAccessToken = "ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe";// In real time
		// this will be
		// Validated
		// this using
		// Authentication
		// Module

		if (accessToken == null || !accessToken.equals(sampleAccessToken)) {
			throw new ValidationException("Invalid Access or Session Token");
		}
		if (transferMoneyRequest == null) {
			throw new ValidationException("Invalid Request");
		}
		return service.transferMoney(transferMoneyRequest);

	}
}
