package com.banking.Dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Acc_ID")
	private int id;
	@Column(name = "Acc_Type")
	private String accountType;
	@Column(name = "Acc_Bal")
	private double accountBal;
	@Column(name = "Acc_Num")
	private long accountNumber;
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBal() {
		return accountBal;
	}
	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
