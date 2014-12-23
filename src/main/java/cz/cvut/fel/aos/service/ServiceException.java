package cz.cvut.fel.aos.service;

/**
 * Exception which is thrown when some error or exception found at service layer.
 *
 * @author Ondřej Krejčíř
 */
public class ServiceException extends Exception {

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}
}
