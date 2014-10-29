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
 * Created by krejcir on 27.10.14.
 */
public class FlightService {

	private String dateformat = "yyyy-MM-dd'T'HH:mm:ssXXX";

	private FlightDAO dao;

	public FlightService() {
		dao = new FlightDAO();
	}

	public ArrayList<Flight> find(int offset, int limit, String order, String filter) {
		return this.entityListToDataList(dao.getAll(offset, limit, order, filter));
	}

	public Flight find(Long id) {
		return this.entityToData(dao.get(id));
	}

	public void delete(Long id) throws PersistenceException {
		dao.delete(id);
	}

	public void create(Flight data) throws PersistenceException, ServiceException {
		dao.create(this.dataToEntity(data));
	}

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
