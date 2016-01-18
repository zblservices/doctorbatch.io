package com.ibm.bigbluebank.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import com.ibm.bigbluebank.model.AccountData;
import com.ibm.bigbluebank.model.CustomerData;
import com.ibm.bigbluebank.model.CustomerDatas;

public class MemoryCustomerDAO implements CustomerDAO {
	private static Map<String, CustomerData> customers = 
			Collections.synchronizedMap( new HashMap<String, CustomerData>() );
	
	static { 
		final String[] names = { 
			    "Vernetta River",
			    "Leila Summerall",
			    "Paige Sharrow",
			    "Cammy Mix",
			    "Malika Brenneman",
			    "Wade Stanfill",
			    "Karleen Herod",
			    "Anna Schaaf",
			    "Julian Bento",
			    "Earleen Rockefeller",
			    "Etsuko Cahill",
			    "Shavon Reese",
			    "Iesha Buis",
			    "Barbie Bramble",
			    "Veronika Bill",
			    "Alena Stults",
			    "Katelin Spinks",
			    "Earlean Nealy",
			    "Chrissy Stroup",
			    "Elsa Ying",
			    "Lucina Marksberry",
			    "Allegra Tubbs",
			    "Pamila Gaver",
			    "Amber Cantero",
			    "Tarah Kierstead",
			    "Deloras Chisum",
			    "Laila Viator",
			    "Cheyenne Kluge",
			    "Earnest Shriver",
			    "Shelton Delucca",
			    "Marilu Wey",
			    "Kyla Hanel",
			    "Deandra Angel",
			    "Rick Topham",
			    "Cheryll Holeman",
			    "Cherri Holts",
			    "Lorena Fryman",
			    "Larraine Ferrer",
			    "Brent Ihle",
			    "Mira Sinclair",
			    "Leonora Vaillancourt",
			    "Kara Brumer",
			    "Kylee Morant",
			    "Gigi Suzuki",
			    "Genaro Dehner",
			    "Michal Verduzco",
			    "Albert Landi",
			    "Dorris Tomey",
			    "Creola Schimmel",
			    "Evon Wheelwright" };
		
		final String[] streets = {
			    "Cedar Street",
			    "Cambridge Road",
			    "Valley View Road",
			    "6th Street West",
			    "Roosevelt Avenue",
			    "Country Lane",
			    "Main Street South",
			    "Windsor Drive",
			    "12th Street East",
			    "Beech Street",
			    "Chapel Street",
			    "Cedar Court",
			    "Church Street North",
			    "Hickory Street",
			    "Route 17",
			    "Spruce Avenue",
			    "Central Avenue",
			    "Hamilton Street",
			    "Canterbury Road",
			    "Church Street",
			    "9th Street",
			    "Route 1",
			    "Edgewood Drive",
			    "College Street",
			    "Route 32",
			    "Main Street East",
			    "Creekside Drive",
			    "Orange Street",
			    "Railroad Avenue",
			    "Durham Road",
			    "Jefferson Avenue",
			    "Broad Street",
			    "Spruce Street",
			    "Fairview Road",
			    "Lafayette Street",
			    "Division Street",
			    "Buttonwood Drive",
			    "Maiden Lane",
			    "8th Street West",
			    "6th Street",
			    "Franklin Avenue",
			    "3rd Avenue",
			    "Center Street",
			    "Delaware Avenue",
			    "Fairview Avenue",
			    "Orchard Avenue",
			    "Brandywine Drive",
			    "Fawn Court",
			    "Marshall Street",
			    "South Street"
		};
		
		AccountDAO acctdao = new MemoryAccountDAO();
		Random r = new Random();
		for ( int i = 0; i < 20; ++i ) {
			CustomerData cd = new CustomerData();
			cd.setCustomerId(String.format("%02d", i+1));
			cd.setFirstName(names[i].split(" ")[0]);
			cd.setLastName(names[i].split(" ")[1]);
			cd.setMiddleName(Character.valueOf((char)(r.nextInt(26)+'a')).toString()+".");
			cd.setStreetAddress(r.nextInt(500) + " " + streets[r.nextInt(streets.length)] );
			cd.setState("NV");
			cd.setCity("Las Vegas");
			cd.setZip("89109");
			
			AccountData[] accts = acctdao.listAccounts( cd.getCustomerId() );
			String[] acctnumbers = new String[ accts.length ];

			for ( int j = 0; j < accts.length; ++j ) {
				acctnumbers[j] = accts[j].getAccountNumber();
			}
			
			cd.setAccountNumbers( acctnumbers );
			customers.put(cd.getCustomerId(), cd);
		}
	}
	
	@Override
	public CustomerData fetchCustomerData(String ssn) {
		if ( customers.containsKey( ssn ) )
			return customers.get(ssn);
		
		return null;
	}

	@Override
	public CustomerData createNewCustomer(CustomerData customerData) {
		if ( customers.containsKey(customerData.getCustomerId()) ) 
			return null;
		
		customers.put( customerData.getCustomerId(), customerData );
		return customerData;
	}

	@Override
	public CustomerDatas listCustomers() {
		CustomerDatas data = new CustomerDatas();
		data.setCustomerdatas(new ArrayList<CustomerData>( customers.values() ) );

		return data;
	}

}
