package exception;


public class qaFrameworkException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public qaFrameworkException(String message) {
		super(message);
	}
	
	public qaFrameworkException(String message, Throwable t) {
		super(message, t);
	}

}

