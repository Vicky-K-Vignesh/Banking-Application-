package com.banking.Dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Bank {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String bankName;
	private long ifsc;
	private String city;
	private long contact;
	@OneToOne(cascade = CascadeType.ALL)
	private Manager manager;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Customer> customers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getIfsc() {
		return ifsc;
	}
	public void setIfsc(long ifsc) {
		this.ifsc = ifsc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}
