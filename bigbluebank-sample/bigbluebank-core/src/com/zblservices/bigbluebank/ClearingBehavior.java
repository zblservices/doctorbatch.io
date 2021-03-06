package com.zblservices.bigbluebank;

import java.util.Properties;
import java.util.logging.Logger;

import com.zblservices.bigbluebank.model.AccountData;
import com.zblservices.bigbluebank.model.DepositRecord;
import com.zblservices.doctorbatch.io.RecordProcessor;

public class ClearingBehavior implements RecordProcessor<DepositRecord,DepositRecord> {
	AccountManager manager = new AccountManager();
	private final static Logger LOG = Logger.getLogger(ClearingBehavior.class.getSimpleName());
	
	private final static String ERROR_CODE_CUSTOMER_MISMATCH = "30001";
	
	@Override
	public int getReturnCode() {
		return 0;
	}

	@Override
	public void initialize(Properties arg0) {
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DepositRecord process(DepositRecord depositRecord) {
		String accountNumber = depositRecord.getAccount_No().trim();

		// Fetch the account data from the account manager ...
		AccountData accountData = manager.fetchAccountData( accountNumber );
		
		if ( accountData == null ) {
			LOG.severe( "Could not find account data for account number: " + accountNumber );
			return depositRecord;
		}
		
		// Validate the customer id ...
		String customerId = depositRecord.getCustomer_Id().trim();
		
		// The customer id should be either the primary or secondary account holder SSN
		if ( ! customerId.equals( accountData.getPrimaryAccountHolderSSN() ) &&
		     ! customerId.equals( accountData.getSecondaryAccountHolderSSN() ) ) {
			// If it doesn't match one of them ... ERROR 30001
			depositRecord.setProcess_Status("E");
			depositRecord.setError_Code( ERROR_CODE_CUSTOMER_MISMATCH );
		}
		else {
			// Update the account balances ...
			accountData.setAvailableBalance( 
					accountData.getAvailableBalance().add( 			// CURRENT AVAIALBLE BALANCE  :PLUS
							depositRecord.getAmount().subtract( 	// TOTAL DEPOSIT AMOUNT       :LESS
									depositRecord.getAvail_Imm() 	// AMOUNT ALREADY AVAILABLE	
			) ) );
			
			// Persist the results
			manager.updateAccountBalances(accountData);
			depositRecord.setProcess_Status("C");
		}
		
		LOG.info( "Processed deposit of " + depositRecord.getAmount() + " in account " + depositRecord.getAccount_No() + " for customer " + depositRecord.getCustomer_Id() + " status: " + depositRecord.getProcess_Status() );
		return depositRecord;
	}

}
