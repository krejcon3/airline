package cz.cvut.fel.aos.persistence;

/**
 * Exception which is thrown when some error found at persitence layer.
 *
 * @author Ondřej Krejčíř
 */
public class PersistenceException extends Exception {

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}
}
