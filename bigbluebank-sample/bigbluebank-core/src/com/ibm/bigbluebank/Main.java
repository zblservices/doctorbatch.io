package com.ibm.bigbluebank;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import com.ibm.bigbluebank.AccountManager;
import com.ibm.bigbluebank.model.AccountData;
import com.ibm.bigbluebank.model.DepositRecord;
import com.ibm.jzos.RDWOutputRecordStream;


public class Main {
	
	public static void main(String[] args) {
		System.out.println( "Generating random deposit data file: " + args[0] );
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream( args[0] );
			RDWOutputRecordStream rdwos = new RDWOutputRecordStream(fos);

			AccountManager manager = new AccountManager();
			Random r = new Random();
			
			for ( int i = 0; i < 10000; ++i ) {
				
				String acctNumber = "005001010"+String.format("%02d", r.nextInt(60));
				AccountData data = manager.fetchAccountData(acctNumber);
				
				DepositRecord record = new DepositRecord();
				record.setAccount_No( acctNumber );
				
				// About 1 in 100 will mismatch on the customer id.
				if ( r.nextInt() % 100 == 1 )
					record.setCustomer_Id( "99999999" );
				else
					record.setCustomer_Id(data.getPrimaryAccountHolderSSN());
				
				record.setAmount( BigDecimal.valueOf( r.nextInt( 500 ) + 500 ) );
				record.setAvail_Imm( BigDecimal.valueOf( r.nextInt(500) ) );
				record.setProcess_Status("U");
				
				byte[] bytes = record.getBytes();
				rdwos.write( bytes );
				System.out.println( "Wrote record: " + bytes.length + " bytes" );
			}
		
			rdwos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

