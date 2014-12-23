package cz.cvut.fel.aos.api;

/**
 * Exception which is thrown for access denied situation.
 *
 * @author Ondřej Krejčíř
 */
public class UnauthorizedException extends Exception {

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}
}
