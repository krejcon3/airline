package cz.cvut.fel.aos.service;

import com.sun.jersey.core.util.Base64;
import cz.cvut.fel.aos.api.UnauthorizedException;
import cz.cvut.fel.aos.entity.UserEntity;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.persistence.UserDAO;

/**
 * Provides User objects
 *
 * @author Ondřej Krejčíř
 */
public class UserService {

	private UserDAO dao;

	public UserService() {
		this.dao = new UserDAO();
	}

	/**
	 * Compare input password with database
	 *
	 * @param pseudoToken input password
	 * @return boolean if is authorized
	 */
	private boolean comparePasswords(String pseudoToken) {
		String[] raw = Base64.base64Decode(pseudoToken).split(":");
		try {
			UserEntity userEntity = this.dao.get(raw[0]);
			return userEntity.getPassword().equals(raw[1]);
		} catch (PersistenceException e) {
			return false;
		}
	}

	/**
	 * Compare input password with database
	 *
	 * @param authorization input password
	 * @return boolean if is authorized
	 * @throws UnauthorizedException
	 */
	public boolean authenticate(String authorization) throws UnauthorizedException {
		if (authorization != null) {
			if (this.comparePasswords(authorization)) {
				return true;
			}
		}
		throw new UnauthorizedException("Authorization requested.");
	}
}
