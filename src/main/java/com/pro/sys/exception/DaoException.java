
package com.pro.sys.exception;

/**
 * DAO layer exception
 * 
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 11L;

	private String msg = "";

	public DaoException ( ) {
		super ( );
	}

	public DaoException (String msg) {
		super (msg);
	}

	public DaoException (Throwable cause) {
		super (cause);
		this.msg = cause.getMessage ( );
	}

	public DaoException (String msg, Throwable cause) {
		super (msg, cause);
		this.msg = msg;
	}

	public String getMessage( ) {
		return msg;
	}
}
