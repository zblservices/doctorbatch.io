package com.ibm.bigbluebank.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customerdata")
@XmlAccessorType( XmlAccessType.FIELD )
public class CustomerData {
	@XmlElement(required=true)
	private String customerId;
	
	@XmlElement(required=true)
	private String firstName;

	@XmlElement(required=false)
	private String middleName;

	@XmlElement(required=true)
	private String lastName;
	
	@XmlElement(required=true)
	private String streetAddress;
	
	@XmlElement(required=true)
	private String city;
	
	@XmlElement(required=true)
	private String state;
	
	@XmlElement(required=true)
	private String zip;
	
	@XmlElement(required=true)
	private String[] accountNumbers;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String[] getAccountNumbers() {
		return accountNumbers;
	}

	public void setAccountNumbers(String[] accountNumbers) {
		this.accountNumbers = accountNumbers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerData other = (CustomerData) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}
	
	
}
