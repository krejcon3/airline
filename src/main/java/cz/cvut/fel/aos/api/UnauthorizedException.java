package cz.cvut.fel.aos.api;

/**
 * Created by krejcir on 2.12.14.
 */
public class UnauthorizedException extends Exception {

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}
}
