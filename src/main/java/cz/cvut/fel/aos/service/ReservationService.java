package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Reservation;
import cz.cvut.fel.aos.entity.ReservationEntity;
import cz.cvut.fel.aos.persistence.PersistenceException;
import cz.cvut.fel.aos.persistence.ReservationDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krejcir on 27.10.14.
 */
public class ReservationService {

	private ReservationDAO dao;

	public ReservationService() {
		dao = new ReservationDAO();
	}

	public ArrayList<Reservation> find() {
		return this.entityListToDataList(dao.getAll());
	}

	public Reservation find(Long id) {
		return this.entityToData(dao.get(id));
	}

	public void delete(Long id) throws PersistenceException {
		dao.delete(id);
	}

	public void create(Reservation data) throws PersistenceException {
		dao.create(this.dataToEntity(data));
	}

	public void update(Long id, Reservation data) throws PersistenceException {
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

	private ArrayList<ReservationEntity> dataListToEntityList(List<Reservation> datalist) {
		ArrayList<ReservationEntity> entityList = new ArrayList<ReservationEntity>();
		for (Reservation data : datalist) {
			entityList.add(this.dataToEntity(data));
		}
		return entityList;
	}

	private Reservation entityToData(ReservationEntity entity) {
		Reservation data = new Reservation();
		if (entity != null) {
			data.setId(entity.getId());
			data.setSeats(entity.getSeats());
			data.setCreated(entity.getCreated());
			data.setFlight(entity.getFlight());
			data.setPassword(entity.getPassword());
			data.setState(entity.getState());
		}
		return data;
	}

	private ReservationEntity dataToEntity(Reservation data) {
		ReservationEntity entity = new ReservationEntity();
		if (data != null) {
			entity.setId(data.getId());
			entity.setSeats(data.getSeats());
			entity.setCreated(data.getCreated());
			entity.setFlight(data.getFlight());
			entity.setPassword(data.getPassword());
			entity.setState(data.getState());
		}
		return entity;
	}
}
