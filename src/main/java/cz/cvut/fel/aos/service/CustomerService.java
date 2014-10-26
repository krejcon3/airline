package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.api.data.Customer;

public interface CustomerService {

	public Customer retrieve(Long id);

    public void delete(Long id);
	
	public void save(Customer customer);
}
