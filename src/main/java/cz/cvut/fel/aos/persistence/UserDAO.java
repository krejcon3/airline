package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.UserEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * User dao for accessing database
 *
 * @author Ondřej Krejčíř
 */
public class UserDAO extends AbstractDAO {

	/**
	 * Get all users
	 *
	 * @return List of user entities
	 */
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT s FROM UserEntity s")
			.getResultList();
	}

	/**
	 * Get the one user by identificator
	 *
	 * @param id identificator of the user
	 * @return UserEntity
	 */
	@SuppressWarnings("unchecked")
	public UserEntity get(Long id) {
		return this.getEntityManager().find(UserEntity.class, id);
	}

	/**
	 * Get the one user by nickname
	 *
	 * @param nickname nickname of the user
	 * @return UserEntity
	 */
	@SuppressWarnings("unchecked")
	public UserEntity get(String nickname) throws PersistenceException {
		List<UserEntity> userEntities = this.getEntityManager()
				.createQuery("SELECT s FROM UserEntity s WHERE s.nickname = :nickname")
				.setParameter("nickname", nickname)
				.getResultList();
		if (!userEntities.isEmpty()) {
			return userEntities.get(0);
		}
		throw new PersistenceException("User not found!");
	}
}
