package com.banking.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.banking.Dto.Customer;

public class CDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Customer saveCustomerDetails(Customer c) {
		if (c != null) {
			et.begin();
			em.persist(c);
			et.commit();
			return c;
		} else
			return null;
	}

	public Customer findCustomer(int customerID) {
		Customer finded = em.find(Customer.class, customerID);
		if (finded != null) {
			return finded;
		}
		return null;
	}

	public Customer updateCustomerDetails(Customer updated) {
		if (updated != null) {
			Customer finded = findCustomer(updated.getId());
			if (finded != null) {
				et.begin();
				em.merge(updated);
				et.commit();
				return updated;
			}
			return null;
		}
		return null;
	}

	public Customer deleteCustomer(int customerId) {
		Customer finded = findCustomer(customerId);
		if (finded != null) {
			et.begin();
			em.remove(finded);
			et.commit();
			return finded;
		}
		return null;
	}

	public List<Customer> getAllCustomerList() {
		Query query = em.createQuery("Select c from Customer c");
		List<Customer> customerList = query.getResultList();
		if (customerList != null) {
			return customerList;
		}
		return null;
	}
}
