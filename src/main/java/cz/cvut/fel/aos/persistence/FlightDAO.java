package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.FlightEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by krejcir on 28.10.14.
 */
public class FlightDAO extends AbstractDAO {

	private String dateformat = "yyyy-MM-dd'T'HH:mm:ssXXX";

	@SuppressWarnings("unchecked")
	public List<FlightEntity> getAll(int offset, int limit, String order, String filter) throws PersistenceException {
		String query = "SELECT f FROM FlightEntity f";

		Date dateFrom = null;
		Date dateTo = null;
		if (filter != null) {
			SimpleDateFormat df = new SimpleDateFormat(this.dateformat);
			String[] params = filter.trim().split(",");
			if (params.length != 2) {
				throw new PersistenceException("Invalid count of params, define From and To dates.");
			}
			String[] from = params[0].trim().split("=");
			String[] to = params[1].trim().split("=");
			try {
				dateFrom = df.parse(from[1].trim());
				dateTo = df.parse(to[1].trim());
			} catch (ParseException e) {
				throw new PersistenceException("Bad dateformat: " + this.dateformat + " " + filter);
			}
		}

		if (filter != null) {
			query += " WHERE f.dateOfDeparture BETWEEN :startDate AND :endDate";
		}

		if (order != null) {
			order.trim();
			order = "f." + order.replace(":", " ");
			query += " ORDER BY " + order;
		}

		if (limit > 0) {
			if (filter != null) {
				return this.getEntityManager()
					.createQuery(query)
					.setParameter("startDate", dateFrom, TemporalType.DATE)
					.setParameter("endDate", dateTo, TemporalType.DATE)
					.setFirstResult(offset)
					.setMaxResults(limit)
					.getResultList();
			}
			return this.getEntityManager()
				.createQuery(query)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
		}
		if (filter != null) {
			return this.getEntityManager()
				.createQuery(query)
				.setParameter("startDate", dateFrom, TemporalType.TIMESTAMP)
				.setParameter("endDate", dateTo, TemporalType.TIMESTAMP)
				.getResultList();
		}
		return this.getEntityManager()
			.createQuery(query)
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
