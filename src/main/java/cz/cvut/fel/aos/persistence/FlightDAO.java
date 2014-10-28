package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.FlightEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by krejcir on 28.10.14.
 */
public class FlightDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<FlightEntity> getAll() {
		return this.getEntityManager()
			.createQuery("SELECT f FROM FlightEntity f")
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public FlightEntity get(Long id) {
		return this.getEntityManager().find(FlightEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public void delete(Long id) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			FlightEntity entity = manager.getReference(FlightEntity.class, id);
			if (entity == null) {
				throw new PersistenceException("Flight not exist.");
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
	public void create(FlightEntity entity) throws PersistenceException {
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
	public void update(FlightEntity updateEntity) throws PersistenceException {
		EntityManager manager = this.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			FlightEntity entity = manager.getReference(FlightEntity.class, updateEntity.getId());
			if (entity == null) {
				throw new PersistenceException("Destination not exist.");
			}
			entity.setName(updateEntity.getName());
			entity.setSeats(updateEntity.getSeats());
			entity.setDateOfDeparture(updateEntity.getDateOfDeparture());
			entity.setDistance(updateEntity.getDistance());
			entity.setFrom(updateEntity.getFrom());
			entity.setTo(updateEntity.getTo());
			entity.setPrice(updateEntity.getPrice());

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
