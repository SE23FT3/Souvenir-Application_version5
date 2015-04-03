package sg.edu.nus.iss.models;

import java.io.Serializable;



public class Customer{

	/*
	 * created by Deepsha on 21stMarch 2015
	 * */
	private String customerName;

	public Customer()
	{
		
	}
	public Customer(String customerName) {
		this.customerName=customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
