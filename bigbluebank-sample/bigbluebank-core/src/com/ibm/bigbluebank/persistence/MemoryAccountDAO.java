package com.ibm.bigbluebank.persistence;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import com.ibm.bigbluebank.model.AccountData;

public class MemoryAccountDAO implements AccountDAO {
	private static Map<String, AccountData> accounts = 
			Collections.synchronizedMap( new HashMap<String, AccountData>() );
	
	static Date generateDateXYearsAgo( int x ) {
		Calendar cal = Calendar.getInstance();
		Random r = new Random();
		
		int days = x*365 + r.nextInt(365) + 1;
		for ( int i = 0; i < days; ++i ) {
			cal.add( Calendar.DATE, -1 );
		}
		
		return cal.getTime();
	}
	
	static { 
		Random r = new Random();

		int accountNumber = 0;
		for ( int i = 0; i < 20; ) {
			String customerId = String.format("%02d", i+1);
			int numberOfAccounts = 3; //r.nextInt(3)+1;
			
			for ( int j = 0; j < numberOfAccounts; ++j ) {
				AccountData data = new AccountData();
				data.setAccountNumber("005001010"+String.format("%02d", accountNumber++));
				data.setAccountBalance( new BigDecimal( "1000.00" ) );
				data.setAvailableBalance( new BigDecimal( "1000.00" ) );
				data.setPrimaryAccountHolderSSN(customerId);
				data.setAccountOpened( generateDateXYearsAgo( r.nextInt(15) ) );
				accounts.put( data.getAccountNumber(), data );			
			}			
			
			++i;
		}
	}
	
	@Override
	public AccountData fetchAccountData( String accountNumber ) {
		if ( accounts.containsKey(accountNumber) )
			return accounts.get(accountNumber);
		
		return null;
	}

	@Override
	public AccountData openAccount( AccountData accountData ) {
		if ( accounts.containsKey(accountData.getAccountNumber()) ) 
			return null;
		
		accounts.put( accountData.getAccountNumber(), accountData );
		return accountData;
	}

	@Override
	public AccountData updateAvailableAccountBalance(AccountData accountData,
			BigDecimal newBalance) {
		AccountData data = accounts.get( accountData.getAccountNumber() );
		data.setAvailableBalance(newBalance);
		
		return data;
	}

	@Override
	public AccountData updateAccountBalance(AccountData accountData,
			BigDecimal newBalance) {
		AccountData data = accounts.get( accountData.getAccountNumber() );
		data.setAccountBalance(newBalance);
		
		return data;
	}

	@Override
	public AccountData[] listAccounts() {
		return accounts.values().toArray( new AccountData[0] );
	}
	
	@Override
	public AccountData[] listAccounts( String ssn ) {
		List<AccountData> accounts = new LinkedList<AccountData>();
		
		for ( AccountData acct : MemoryAccountDAO.accounts.values() ) {
			if ( ssn.equals( acct.getPrimaryAccountHolderSSN() ) || ssn.equals( acct.getSecondaryAccountHolderSSN() ) ) {
				accounts.add(acct);
			}
		}
		
		return accounts.toArray(new AccountData[0]);
	}

}
