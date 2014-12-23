package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.ReservationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Reservation dao for accessing database
 *
 * @author Ondřej Krejčíř
 */
public class ReservationDAO extends AbstractDAO {

	/**
	 * Get all reservations
	 *
	 * @return List of all reservations
	 */
	@SuppressWarnings("unchecked")
	public List<ReservationEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT r FROM ReservationEntity r")
			.getResultList();
	}

	/**
	 * Get the one reservation
	 *
	 * @param id identificator of the reservation
	 * @return ReservationEntity
	 */
	@SuppressWarnings("unchecked")
	public ReservationEntity get(Long id) {
		return this.getEntityManager().find(ReservationEntity.class, id);
	}

	/**
	 * Delete the reservation
	 *
	 * @param id identificator of the reservation
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void delete(Long id) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ReservationEntity entity = manager.getReference(ReservationEntity.class, id);
			if (entity == null) {
				throw new PersistenceException("Reservation not exist.");
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

	/**
	 * Create new reservation
	 *
	 * @param entity entity of new reservation
	 * @throws PersistenceException
	 */
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

	/**
	 * Update the reservation entity
	 *
	 * @param updateEntity entity to update
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void update(ReservationEntity updateEntity) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			ReservationEntity entity = manager.getReference(ReservationEntity.class, updateEntity.getId());
			if (entity == null) {
				throw new PersistenceException("Reservation not exist.");
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
