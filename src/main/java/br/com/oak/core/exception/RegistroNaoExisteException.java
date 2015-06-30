package br.com.oak.core.exception;

public class RegistroNaoExisteException extends OakException {

	private static final long serialVersionUID = 103123027220134038L;

	public RegistroNaoExisteException() {
		super();
	}

	public RegistroNaoExisteException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RegistroNaoExisteException(String msg) {
		super(msg);
	}

	public RegistroNaoExisteException(Throwable cause) {
		super(cause);
	}
}