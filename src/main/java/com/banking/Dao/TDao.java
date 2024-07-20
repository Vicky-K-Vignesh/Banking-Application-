package com.banking.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.banking.Dto.Transaction;

public class TDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Transaction saveTransaction(Transaction t) {
		if (t != null) {
			et.begin();
			em.persist(t);
			et.commit();
			return t;
		}
		return null;
	}

	public Transaction findTransaction(int transactionId) {
		Transaction finded = em.find(Transaction.class, transactionId);
		if (finded != null)
			return finded;
		return null;
	}

}
