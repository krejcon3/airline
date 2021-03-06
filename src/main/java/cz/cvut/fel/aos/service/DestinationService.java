package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Destination;
import cz.cvut.fel.aos.entity.DestinationEntity;
import cz.cvut.fel.aos.persistence.DestinationDAO;
import cz.cvut.fel.aos.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides Destination objects
 *
 * @author Ondřej Krejčíř
 */
public class DestinationService {

	private DestinationDAO dao;

	public DestinationService() {
		dao = new DestinationDAO();
	}

	/**
	 * Find all destinations
	 *
	 * @return List of all destinations
	 */
	public ArrayList<Destination> find() {
		return this.entityListToDataList(dao.getAll());
	}

	/**
	 * Find the destination
	 *
	 * @param id identificator of the destination
	 * @return Destination
	 */
	public Destination find(Long id) {
		return this.entityToData(dao.get(id));
	}

	/**
	 * Deletes the destination
	 *
	 * @param id identificator of the destination
	 * @throws PersistenceException
	 */
	public void delete(Long id) throws PersistenceException {
		dao.delete(id);
	}

	/**
	 * Save Destinationa as DestinationEntity
	 *
	 * @param data Destination to create
	 * @throws PersistenceException
	 */
	public void create(Destination data) throws PersistenceException {
		dao.create(this.dataToEntity(data));
	}

	/**
	 * Update DestinationEntity
	 *
	 * @param id identificator of the destination to update
	 * @param data Destination content to be updated
	 * @throws PersistenceException
	 */
	public void update(Long id, Destination data) throws PersistenceException {
		data.setId(id);
		dao.update(this.dataToEntity(data));
	}

	private ArrayList<Destination> entityListToDataList(List<DestinationEntity> entityList) {
		ArrayList<Destination> dataList = new ArrayList<Destination>();
		for (DestinationEntity entity : entityList) {
			dataList.add(this.entityToData(entity));
		}
		return dataList;
	}

	private ArrayList<DestinationEntity> dataListToEntityList(List<Destination> datalist) {
		ArrayList<DestinationEntity> entityList = new ArrayList<DestinationEntity>();
		for (Destination data : datalist) {
			entityList.add(this.dataToEntity(data));
		}
		return entityList;
	}

	private Destination entityToData(DestinationEntity entity) {
		Destination data = new Destination();
		if (entity != null) {
			data.setId(entity.getId());
			data.setName(entity.getName());
			data.setLatitude(entity.getLatitude());
			data.setLongitude(entity.getLongitude());
			data.setUrl("/destination/" + entity.getId());
		}
		return data;
	}

	private DestinationEntity dataToEntity(Destination data) {
		DestinationEntity entity = new DestinationEntity();
		if (data != null) {
			entity.setId(data.getId());
			entity.setName(data.getName());
			entity.setLatitude(data.getLatitude());
			entity.setLongitude(data.getLongitude());
		}
		return entity;
	}
}
