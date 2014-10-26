package cz.cvut.fel.aos.persistence;

import cz.cvut.fel.aos.entity.CustomerEntity;

public interface CustomerDao {
	
	public CustomerEntity retrieve(Long id);

    public void delete(Long id);
	
	public void save(CustomerEntity customer);
}
