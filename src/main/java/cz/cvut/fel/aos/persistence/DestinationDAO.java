package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.DestinationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by krejcir on 28.10.14.
 */
public class DestinationDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<DestinationEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT d FROM DestinationEntity d")
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public DestinationEntity get(Long id) {
		return this.getEntityManager().find(DestinationEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public void delete(Long id) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			DestinationEntity entity = manager.getReference(DestinationEntity.class, id);
			if (entity == null) {
				throw new PersistenceException("Destination not exist.");
			}
			manager.remove(entity);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
			throw new PersistenceException(e.getMessage());
		} catch (Exception e) {
			transaction.rollback();
			throw new PersistenceException("Service not available.");
		} finally {
			manager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void create(DestinationEntity entity) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			manager.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new PersistenceException("Service not available.");
		} finally {
			manager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void update(DestinationEntity updateEntity) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			DestinationEntity entity = manager.getReference(DestinationEntity.class, updateEntity.getId());
			if (entity == null) {
				throw new PersistenceException("Destination not exist.");
			}
			entity.setName(updateEntity.getName());
			entity.setLatitude(updateEntity.getLatitude());
			entity.setLongitude(updateEntity.getLongitude());

			manager.merge(entity);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
			throw new PersistenceException(e.getMessage());
		} catch (Exception e) {
			transaction.rollback();
			throw new PersistenceException("Service not available.");
		} finally {
			manager.close();
		}
	}
}
