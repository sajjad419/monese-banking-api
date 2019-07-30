package com.monese.api.banking.exception;

import javax.naming.AuthenticationException;
/**
 * @author C.SajjadHussain
 *
 */
public class ValidationException extends AuthenticationException {

	private static final long serialVersionUID = 9163697976465670129L;

	public ValidationException(String message) {
		super(message);
	}

}