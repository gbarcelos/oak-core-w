package br.com.oak.core.exception;

public class OakException extends RuntimeException {

	private static final long serialVersionUID = -2441466312758069508L;

	public OakException() {
		super();
	}

	public OakException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OakException(String msg) {
		super(msg);
	}

	public OakException(Throwable cause) {
		super(cause);
	}
}