package com.ibm.bigbluebank;

import java.math.BigDecimal;

import com.ibm.bigbluebank.model.AccountData;
import com.ibm.bigbluebank.persistence.AccountDAO;
import com.ibm.bigbluebank.persistence.MemoryAccountDAO;

public class AccountManager {
	private static AccountDAO dao = new MemoryAccountDAO();
	
	public AccountData[] listAccounts() {
		return dao.listAccounts();
	}
	
	public AccountData fetchAccountData(final String accountNumber) {
		return dao.fetchAccountData(accountNumber);
	}

	public AccountData openAccount(final AccountData accountData) {
		return dao.openAccount(accountData);
	}

	public AccountData depositFunds( final AccountData account, final BigDecimal depositAmount ) {
		AccountData accountData = account;

		accountData = invokeBusinessRules( 
				"/BigBankRuleAppXX/BigBankRulesXX", 
				account, 
				depositAmount);
		
				
		
		return accountData;
	}

	public static BigDecimal min( BigDecimal a, BigDecimal b ) {
		return a.min(b);
	}
	
	private AccountData invokeBusinessRules( String rulePath, AccountData data, BigDecimal depositAmount ) {

		
		return data;
	}
	
	public void updateAccountBalances( AccountData data ) {
		dao.updateAccountBalance(data, data.getAccountBalance() );
		dao.updateAvailableAccountBalance(data, data.getAvailableBalance() );
	}
}
