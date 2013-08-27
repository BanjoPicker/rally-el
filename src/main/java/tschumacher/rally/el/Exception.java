package tschumacher.rally.el;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class Exception extends RuntimeException {

	public Exception(String message) {
		super(message);
	}

	public Exception(String message, Throwable throwable) {
		super(message, throwable);
	}
}
