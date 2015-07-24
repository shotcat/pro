
package com.pro.sys.exception;

/**
 * Authentication exception
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 3565115502379142992L;

	public AuthenticationException () {
		super();
	}

	public AuthenticationException (String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException (String message) {
		super(message);
	}

	public AuthenticationException (Throwable cause) {
		super(cause);
	}

}
