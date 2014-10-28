package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.ReservationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by krejcir on 28.10.14.
 */
public class ReservationDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<ReservationEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT r FROM ReservationEntity r")
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ReservationEntity get(Long id) {
		return this.getEntityManager().find(ReservationEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public void delete(Long id) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ReservationEntity entity = manager.getReference(ReservationEntity.class, id);
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
	public void create(ReservationEntity entity) throws PersistenceException {
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
	public void update(ReservationEntity updateEntity) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			ReservationEntity entity = manager.getReference(ReservationEntity.class, updateEntity.getId());
			if (entity == null) {
				throw new PersistenceException("Destination not exist.");
			}
			entity.setSeats(updateEntity.getSeats());
			entity.setCreated(updateEntity.getCreated());
			entity.setFlight(updateEntity.getFlight());
			entity.setPassword(updateEntity.getPassword());
			entity.setState(updateEntity.getState());

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
