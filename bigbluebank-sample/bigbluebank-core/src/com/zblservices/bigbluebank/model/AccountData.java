package com.zblservices.bigbluebank.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="accountdata")
@XmlAccessorType( XmlAccessType.FIELD )
public class AccountData {
	@XmlElement(required=true)
	private String accountNumber;

	@XmlElement(required=true)
	private BigDecimal accountBalance;
	
	@XmlElement(required=true)
	private BigDecimal availableBalance;
	
	@XmlElement(required=true)
	private String primaryAccountHolderSSN;
	
	@XmlElement(required=false)
	private String secondaryAccountHolderSSN;
	
	@XmlElement(required=true)
	private Date accountOpened;

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getAccountOpened() {
		return accountOpened;
	}

	public void setAccountOpened(Date accountOpened) {
		this.accountOpened = accountOpened;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getPendingAccountBalance() {
		return accountBalance;
	}

	public void setPendingAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public String getPrimaryAccountHolderSSN() {
		return primaryAccountHolderSSN;
	}

	public void setPrimaryAccountHolderSSN(String primaryAccountHolderSSN) {
		this.primaryAccountHolderSSN = primaryAccountHolderSSN;
	}

	public String getSecondaryAccountHolderSSN() {
		return secondaryAccountHolderSSN;
	}

	public void setSecondaryAccountHolderSSN(String secondaryAccountHolderSSN) {
		this.secondaryAccountHolderSSN = secondaryAccountHolderSSN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
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
		AccountData other = (AccountData) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		return true;
	}
	
	
}
