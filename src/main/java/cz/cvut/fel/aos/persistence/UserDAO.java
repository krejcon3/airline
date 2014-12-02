package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.UserEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by krejcir on 28.10.14.
 */
public class UserDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT s FROM UserEntity s")
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public UserEntity get(Long id) {
		return this.getEntityManager().find(UserEntity.class, id);
	}

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
