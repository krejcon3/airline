package cz.cvut.fel.aos.service;

import com.sun.jersey.core.util.Base64;
import cz.cvut.fel.aos.api.UnauthorizedException;
import cz.cvut.fel.aos.entity.UserEntity;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.persistence.UserDAO;

/**
 * Created by krejcir on 30.11.14.
 */
public class UserService {

	private UserDAO dao;

	public UserService() {
		this.dao = new UserDAO();
	}

	private boolean checkItSmallGorilla(String pseudoToken) {
		String[] raw = Base64.base64Decode(pseudoToken).split(":");
		try {
			UserEntity userEntity = this.dao.get(raw[0]);
			return userEntity.getPassword().equals(raw[1]);
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean authenticate(String authorization) throws UnauthorizedException {
		if (authorization != null) {
			if (this.checkItSmallGorilla(authorization)) {
				return true;
			}
		}
		throw new UnauthorizedException("Authorization requested.");
	}
}
