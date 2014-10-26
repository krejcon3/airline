package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Customer;
import cz.cvut.fel.aos.entity.CustomerEntity;
import cz.cvut.fel.aos.persistence.CustomerDao;
import cz.cvut.fel.aos.persistence.CustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao = new CustomerDaoImpl();
	
	public Customer retrieve(Long id) {
		return entityToCustomer(customerDao.retrieve(id));
	}

    public void delete(Long id) {
        customerDao.delete(id);
    }

	public void save(Customer customer) {
		customerDao.save(customerToEntity(customer));
	}

	// =========== Helpers ================

	private Customer entityToCustomer(CustomerEntity entity) {
		Customer customer = new Customer();

		if (entity != null) {
			customer.setId(entity.getId());
			customer.setFirstName(entity.getFirstName());
			customer.setLastName(entity.getLastName());
		}

		return customer;
	}

	private CustomerEntity customerToEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();

		if (customer != null) {
			entity.setId(customer.getId());
			entity.setFirstName(customer.getFirstName());
			entity.setLastName(customer.getLastName());
		}

		return entity;
	}
}
