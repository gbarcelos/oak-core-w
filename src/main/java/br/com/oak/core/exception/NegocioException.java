package br.com.oak.core.exception;

public class NegocioException extends OakException {

	private static final long serialVersionUID = -7467322943429890835L;

	public NegocioException() {
		super();
	}

	public NegocioException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NegocioException(String msg) {
		super(msg);
	}

	public NegocioException(Throwable cause) {
		super(cause);
	}
}