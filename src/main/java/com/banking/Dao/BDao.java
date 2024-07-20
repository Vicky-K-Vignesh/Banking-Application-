package com.banking.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.banking.Dto.Bank;

public class BDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Bank saveBank(Bank b) {
		if (b != null) {
			et.begin();
			em.persist(b);
			et.commit();
			return b;
		} else
			return null;
	}

	public Bank findBankDetail(int bankId) {
		Bank b = em.find(Bank.class, bankId);
		if (b != null) {
			return b;
		} else {
			return null;
		}
	}

	public List<Bank> getAllBankList() {
		Query query = em.createQuery("Select b from Bank b");
		List<Bank> bankList = query.getResultList();
		if (!(bankList.isEmpty() || bankList == null)) {
			return bankList;
		} else {
			return null;
		}
	}

	public Bank deleteBankDetail(int bankId) {
		Bank b = em.find(Bank.class, bankId);
		if (b != null) {
			em.remove(b);
			return b;
		} else {
			return null;
		}
	}

	public Bank updateBankDetails(Bank updatedBank) {
		Bank bankInDb = em.find(Bank.class, updatedBank.getId());
		if (bankInDb != null) {
			et.begin();
			em.merge(updatedBank);
			et.commit();
		}
		return null;
	}

}
