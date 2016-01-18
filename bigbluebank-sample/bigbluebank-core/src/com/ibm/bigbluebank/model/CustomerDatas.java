package com.ibm.bigbluebank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customerdatas")
public class CustomerDatas {
	@XmlElement(name="customerdata")
	private List<CustomerData> customerdata;

	public List<CustomerData> getCustomerdatas() {
		return customerdata;
	}

	public void setCustomerdatas(List<CustomerData> customerdatas) {
		this.customerdata = customerdatas;
	}
}
