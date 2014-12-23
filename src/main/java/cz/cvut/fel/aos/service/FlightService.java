package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Flight;
import cz.cvut.fel.aos.entity.FlightEntity;
import cz.cvut.fel.aos.persistence.FlightDAO;
import cz.cvut.fel.aos.persistence.PersistenceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides Flight objects
 *
 * @author Ondřej Krejčíř
 */
public class FlightService {

	private String dateformat = "yyyy-MM-dd'T'HH:mm:ssXXX";

	private FlightDAO dao;

	public FlightService() {
		dao = new FlightDAO();
	}

	/**
	 * Find all flights
	 *
	 * @param offset offset of the flight
	 * @param limit limit (count) of flights to get
	 * @param order two part string: 'column:[asc|desc]'
	 * @param filter date filter, in example: dateOfDepartureFrom=2013-02-27T02:04:46+01:00,dateOfDepartureTo=2013-02-27T03:04:46+01:00
	 * @return List of Flights
	 * @throws PersistenceException
	 */
	public ArrayList<Flight> find(int offset, int limit, String order, String filter) throws PersistenceException {
		return this.entityListToDataList(dao.getAll(offset, limit, order, filter));
	}

	/**
	 * Find the flight
	 *
	 * @param id identificator of the flight
	 * @return Flight
	 */
	public Flight find(Long id) {
		return this.entityToData(dao.get(id));
	}

	/**
	 * Deletes the flight
	 *
	 * @param id identificator of the flight
	 * @throws PersistenceException
	 */
	public void delete(Long id) throws PersistenceException {
		dao.delete(id);
	}

	/**
	 * Save Flight as FlightEntity
	 *
	 * @param data Flight to create
	 * @throws PersistenceException
	 */
	public void create(Flight data) throws PersistenceException, ServiceException {
		dao.create(this.dataToEntity(data));
	}

	/**
	 * Update FlightEntity
	 *
	 * @param id identificator of the flight to update
	 * @param data Flight content to be updated
	 * @throws PersistenceException
	 */
	public void update(Long id, Flight data) throws PersistenceException, ServiceException {
		data.setId(id);
		dao.update(this.dataToEntity(data));
	}

	private ArrayList<Flight> entityListToDataList(List<FlightEntity> entityList) {
		ArrayList<Flight> dataList = new ArrayList<Flight>();
		for (FlightEntity entity : entityList) {
			dataList.add(this.entityToData(entity));
		}
		return dataList;
	}

	private ArrayList<FlightEntity> dataListToEntityList(List<Flight> datalist) throws ServiceException {
		ArrayList<FlightEntity> entityList = new ArrayList<FlightEntity>();
		for (Flight data : datalist) {
			entityList.add(this.dataToEntity(data));
		}
		return entityList;
	}

	private Flight entityToData(FlightEntity entity) {
		SimpleDateFormat df = new SimpleDateFormat(this.dateformat);
		Flight data = new Flight();
		if (entity != null) {
			data.setId(entity.getId());
			data.setName(entity.getName());
			data.setDistance(entity.getDistance());
			data.setDateOfDeparture(df.format(entity.getDateOfDeparture()));
			data.setSeats(entity.getSeats());
			data.setTo(entity.getTo());
			data.setFrom(entity.getFrom());
			data.setPrice(entity.getPrice());
			data.setUrl("/flight/" + entity.getId());
		}
		return data;
	}

	private FlightEntity dataToEntity(Flight data) throws ServiceException {
		SimpleDateFormat df = new SimpleDateFormat(this.dateformat);
		FlightEntity entity = new FlightEntity();
		try {
			if (data != null) {
				entity.setId(data.getId());
				entity.setName(data.getName());
				entity.setDistance(data.getDistance());
				entity.setDateOfDeparture(df.parse(data.getDateOfDeparture()));
				entity.setSeats(data.getSeats());
				entity.setTo(data.getTo());
				entity.setFrom(data.getFrom());
				entity.setPrice(data.getPrice());
			}
		} catch (ParseException e) {
			throw new ServiceException("Bad dateformat: " + this.dateformat + " " + data.getDateOfDeparture());
		}
		return entity;
	}
}
