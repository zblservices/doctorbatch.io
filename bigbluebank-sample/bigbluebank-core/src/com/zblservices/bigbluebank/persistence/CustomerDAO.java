package com.zblservices.bigbluebank.persistence;

import com.zblservices.bigbluebank.model.CustomerData;
import com.zblservices.bigbluebank.model.CustomerDatas;

public interface CustomerDAO {
	/**
	 * Fetches the customer data for the given social security number ... 
	 * 
	 * @param ssn The customer's social security number. 
	 * @return A CustomerData object representing the customer, or null if the customer is not found.
	 */
	public CustomerData fetchCustomerData( String ssn );

	/**
	 * Stores a new customer in the persistent store.
	 * 
	 * @param customerData The CustomerData object representing the new customer.
	 * @return The same CustomerData reference if successful, and null otherwise.
	 */
	public CustomerData createNewCustomer( CustomerData customerData );
	
	/**
	 * Lists all known customers
	 */
	public CustomerDatas listCustomers();
}
