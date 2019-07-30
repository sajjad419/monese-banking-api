package com.monese.api.banking.tests.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.monese.api.banking.MoneseTestApplication;
import com.monese.api.banking.entity.User;
import com.monese.api.banking.pojo.TransferMoneyRequest;
import com.monese.api.banking.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author C.SajjadHussain
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoneseTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TransferMoneyIntegrationTest {
	@Value("${local.server.port}")
	private int port;

	private static final TestRestTemplate REST = new TestRestTemplate();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	String baseUrl = "http://localhost:%s";

	@Before
	public void setUp() throws IOException {
		TestUtils.createDBAndData(jdbcTemplate.getDataSource(), true);
	}

	@After
	public void tearDown() {
		TestUtils.cleanUpData(jdbcTemplate.getDataSource(), true);
	}

	/**
	 * Transfer Money Test Cases
	 */

	@Test
	public void testShouldTransferMoneyWith201Response_1()
			throws JsonParseException, JsonMappingException, IOException {
		String accessToken = "ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe";

		String fromAccountNumber = "1234567890123456";
		String toAccountNumber = "1234567890123457";
		Double transferAmount = 1500.0;
		User fromUserBeforeUpdate = userRepository.findByAccountNumber(fromAccountNumber);
		User toUserBeforeUpdate = userRepository.findByAccountNumber(toAccountNumber);
		Double fromUserBalance = fromUserBeforeUpdate.getCurrentBalance();
		Double toUserBalance = toUserBeforeUpdate.getCurrentBalance();

		TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest();
		transferMoneyRequest.setFromAccountNumber(fromAccountNumber);
		transferMoneyRequest.setToAccountNumber(toAccountNumber);
		transferMoneyRequest.setTransferAmount(transferAmount);

		String uriPostFix = "/v1/api/users/transfermoney";

		String url = String.format(baseUrl + uriPostFix, port);

		URI uri = URI.create(url);

		System.out.println("POST >> TransferMoneyRequest URI >> Test Case 1: {} " + uri.toString());

		HttpEntity<TransferMoneyRequest> request = new HttpEntity<TransferMoneyRequest>(transferMoneyRequest,
				TestUtils.createHeaders(accessToken));

		ResponseEntity<String> response = REST.exchange(url, HttpMethod.POST, request, String.class);

		System.out.println("TransferMoneyResponse >> Test Case 1 >> {}" + response);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertTrue(response.getBody() != null);
		assertTrue(response.getBody().toString().indexOf("Transaction Successfull Transaction ID:") >= 0);

		User fromUserAfterUpdate = userRepository.findByAccountNumber(fromAccountNumber);
		User toUserAfterUpdate = userRepository.findByAccountNumber(toAccountNumber);
		// Verifying From user Balance
		assertTrue(fromUserBalance - transferAmount == fromUserAfterUpdate.getCurrentBalance());
		// Verifying To user Balance
		assertTrue(toUserBalance + transferAmount == toUserAfterUpdate.getCurrentBalance());
	}

}
