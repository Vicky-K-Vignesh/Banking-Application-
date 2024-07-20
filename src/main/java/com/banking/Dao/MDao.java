package com.banking.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.banking.Dto.Bank;
import com.banking.Dto.Manager;

public class MDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Manager saveManagerDetails(Manager m) {
		if(m!=null) {
			et.begin();
			em.persist(m);
			et.commit();
			return m;
		}else return null;
	}
	
	public Manager findManagerDetails(int managerId) {
		Manager finded = em.find(Manager.class, managerId);
		if(finded!=null) {
			return finded;
		}else return null;
	}
	
	public Manager updateManagerDetails(Manager updatedManager) {
		if(updatedManager!=null) {
			Manager finded = em.find(Manager.class, updatedManager.getId());
			if(finded!=null) {
				et.begin();
				em.merge(updatedManager);
				et.commit();
				return updatedManager;
			}
			return null;
		}return null;
	}
	
	public Manager deleteManagerDetails(int managerId) {
		Manager finded = findManagerDetails(managerId);
		if(finded!=null) {
			Bank b = finded.getBank();
			if(b!=null) {
				b.setManager(null);
				et.begin();
				em.merge(b);
				et.commit();
			}
			et.begin();
			em.remove(finded);
			et.commit();
			return finded;
		}return null;
	}
	
	public List<Manager> getAllManagersList(){
		Query query = em.createQuery("Select m from Manager m");
		List<Manager> managerList = query.getResultList();
		if(managerList!=null) {
			return managerList;
		}return null;
	}
}
