package com.banking.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.banking.Dto.Account;

public class ADao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Account saveAccount(Account a) {
		if (a != null) {
			et.begin();
			em.persist(a);
			et.commit();
			return a;
		}
		return null;
	}

	public Account findAccount(int accountId) {
		Account finded = em.find(Account.class, accountId);
		if (finded != null)
			return finded;
		return null;
	}

	public Account updateAccount(Account updated) {
		Account finded = findAccount(updated.getId());
		if (finded != null) {
			et.begin();
			em.merge(updated);
			et.commit();
			return updated;
		}
		return null;
	}

	public Account deleteAccount(int accountId) {
		Account finded = findAccount(accountId);
		if (finded != null) {
			et.begin();
			em.remove(finded);
			et.commit();
			return finded;
		}
		return null;
	}

	public List<Account> getAllAccountsList() {
		Query query = em.createQuery("Select a from Account a");
		List<Account> accountsList = query.getResultList();
		if (accountsList != null)
			return accountsList;
		return null;
	}
}
