package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Reservation;
import cz.cvut.fel.aos.entity.ReservationEntity;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.persistence.ReservationDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides Reservation objects
 *
 * @author Ondřej Krejčíř
 */
public class ReservationService {

	private String dateformat = "yyyy-MM-dd'T'HH:mm:ssXXX";

	private ReservationDAO dao;

	public ReservationService() {
		dao = new ReservationDAO();
	}

	/**
	 * Find all reservations
	 *
	 * @return List of all reservations
	 */
	public ArrayList<Reservation> find() {
		return this.entityListToDataList(dao.getAll());
	}

	/**
	 * Find the reservation
	 *
	 * @param id identificator of the reservation
	 * @return Destination
	 */
	public Reservation find(Long id) {
		return this.entityToData(dao.get(id));
	}

	/**
	 * Deletes the reservation
	 *
	 * @param id identificator of the reservation
	 * @throws PersistenceException
	 */
	public void delete(Long id) throws PersistenceException {
		dao.delete(id);
	}

	/**
	 * Save Reservation as ReservationEntity
	 *
	 * @param data Reservation to create
	 * @throws PersistenceException
	 */
	public void create(Reservation data) throws PersistenceException, ServiceException  {
		dao.create(this.dataToEntity(data));
	}

	/**
	 * Update ReservationEntity
	 *
	 * @param id identificator of the reservation to update
	 * @param data Reservation content to be updated
	 * @throws PersistenceException
	 */
	public void update(Long id, Reservation data) throws PersistenceException, ServiceException  {
		data.setId(id);
		dao.update(this.dataToEntity(data));
	}

	private ArrayList<Reservation> entityListToDataList(List<ReservationEntity> entityList) {
		ArrayList<Reservation> dataList = new ArrayList<Reservation>();
		for (ReservationEntity entity : entityList) {
			dataList.add(this.entityToData(entity));
		}
		return dataList;
	}

	private ArrayList<ReservationEntity> dataListToEntityList(List<Reservation> datalist) throws ServiceException {
		ArrayList<ReservationEntity> entityList = new ArrayList<ReservationEntity>();
		for (Reservation data : datalist) {
			entityList.add(this.dataToEntity(data));
		}
		return entityList;
	}

	private Reservation entityToData(ReservationEntity entity) {
		SimpleDateFormat df = new SimpleDateFormat(this.dateformat);
		Reservation data = new Reservation();
		if (entity != null) {
			data.setId(entity.getId());
			data.setSeats(entity.getSeats());
			data.setCreated(df.format(entity.getCreated()));
			data.setFlight(entity.getFlight());
			data.setPassword(entity.getPassword());
			data.setState(entity.getState());
			data.setUrl("/reservation/" + entity.getId());
		}
		return data;
	}

	private ReservationEntity dataToEntity(Reservation data) throws ServiceException {
		SimpleDateFormat df = new SimpleDateFormat(this.dateformat);
		ReservationEntity entity = new ReservationEntity();
		try {
			if (data != null) {
				entity.setId(data.getId());
				entity.setSeats(data.getSeats());
				entity.setCreated(df.parse(data.getCreated()));
				entity.setFlight(data.getFlight());
				entity.setPassword(data.getPassword());
				entity.setState(data.getState());
			}
		} catch (ParseException e) {
			throw new ServiceException("Bad dateformat: " + this.dateformat + " " + data.getCreated());
		}
		return entity;
	}
}
