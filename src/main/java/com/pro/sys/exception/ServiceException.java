
package com.pro.sys.exception;

/**
 * Service layer exception
 * 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 12L;

	private String msg;

	public ServiceException ( ) {
		super ( );
	}

	public ServiceException (String msg) {
		super (msg);
	}

	public ServiceException (Throwable cause) {
		super (cause);
		this.msg = cause.getMessage ( );
	}

	public ServiceException (String msg, Throwable cause) {
		super (msg, cause);
		this.msg = msg;
	}

	public String getMessage( ) {
		return msg;
	}
}
