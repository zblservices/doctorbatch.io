package com.zblservices.bigbluebank.persistence;

import java.math.BigDecimal;

import com.zblservices.bigbluebank.model.AccountData;

public interface AccountDAO {
	/**
	 * Fetches the customer data for the given social security number ... 
	 * 
	 * @param ssn The customer's social security number. 
	 * @return A CustomerData object representing the customer, or null if the customer is not found.
	 */
	public AccountData fetchAccountData( String accountNumber );

	/**
	 * Stores a new customer in the persistent store.
	 * 
	 * @param customerData The CustomerData object representing the new customer.
	 * @return The same CustomerData reference if successful, and null otherwise.
	 */
	public AccountData openAccount( AccountData accountData );
	
	/**
	 * Updates the account's available balance.
	 * 
	 * @param accountData
	 * @param newBalance
	 * @return
	 */
	public AccountData updateAvailableAccountBalance( AccountData accountData, BigDecimal newBalance );
	
	/**
	 * Updates the account's balance.
	 * 
	 * @param accountData
	 * @param newBalance
	 * @return
	 */
	public AccountData updateAccountBalance( AccountData accountData, BigDecimal newBalance );
	
	/**
	 * Returns an array of all known accounts.
	 * 
	 * @return
	 */
	public AccountData[] listAccounts();
	
	/**
	 * Obtains accounts where the customer's SSN is primary or secondary.
	 * 
	 * @param customerSSN
	 * @return
	 */
	public AccountData[] listAccounts( String customerSSN );
}
