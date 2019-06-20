package exception;

public class qaTestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public qaTestException(String message) {
		super(message);
	}
	
	public qaTestException(String message, Throwable t) {
		super(message, t);
	}

}
