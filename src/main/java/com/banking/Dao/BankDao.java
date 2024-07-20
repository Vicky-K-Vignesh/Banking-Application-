package com.banking.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.banking.Dto.Bank;
import com.banking.Dto.Customer;
import com.banking.Dto.Manager;

public class BankDao {
	BDao dao = new BDao();
	MDao managerDao = new MDao();

	public void saveBank(Scanner sc) {
		Bank b = new Bank();
		System.out.println("Enter the Bank Name :");
		b.setBankName(sc.nextLine());
		System.out.println("Enter the City Where Bank is located :");
		b.setCity(sc.nextLine());
		System.out.println("Enter the Bank Contact No :");
		b.setContact(sc.nextLong());
		System.out.println("Enter the Bank IFSC Code :");
		b.setIfsc(sc.nextLong());
		Manager manager = null;
		b.setManager(manager);
		List<Customer> customersList = new ArrayList<>();
		b.setCustomers(customersList);
		if (dao.saveBank(b) != null) {
			System.out.println("Bank Details saved Successful...");
		} else {
			System.out.println("Error in saving Bank Details, Please Try Again");
		}
	}

	public void findSpecificBank(Scanner sc) {
		System.out.println("Enter the Bank id that you want to find :");
		int idToFind = sc.nextInt();
		Bank fetchedData = dao.findBankDetail(idToFind);
		if (fetchedData != null) {
			System.out.println(fetchedData.getId() + " " + fetchedData.getBankName() + " " + fetchedData.getContact()
					+ " " + fetchedData.getCity() + " Manager's Name: " + fetchedData.getManager().getName());
		} else {
			System.out.println("No Such Bank Details Found");
		}
	}

	public void updateBankDetails(Scanner sc) {
		System.out.println("Enter the Bank id you want to update");
		int id = sc.nextInt();
		Bank toBeUpdated = dao.findBankDetail(id);
		if (toBeUpdated != null) {
			System.out.println("Enter the Option You want to Update");
			System.out.println("1 ---->  Update Bank Name");
			System.out.println("2 ---->  Update Bank IFSC");
			System.out.println("3 ---->  Update Bank City");
			System.out.println("4 ---->  Update Bank Contact");
			int op = sc.nextInt();
			switch (op) {
			case 1: {
				System.out.println("Enter the Bank Name");
				toBeUpdated.setBankName(sc.nextLine());
				Bank b = dao.updateBankDetails(toBeUpdated);
				if (b != null)
					System.out.println("Bank Details Updated");
				else
					System.out.println("Error in Changing Bank Details");
				break;
			}
			case 2: {
				System.out.println("Enter the Bank IFSC");
				toBeUpdated.setIfsc(sc.nextLong());
				Bank b = dao.updateBankDetails(toBeUpdated);
				if (b != null)
					System.out.println("Bank Details Updated");
				else
					System.out.println("Error in Changing Bank Details");
				break;
			}
			case 3: {
				System.out.println("Enter the Bank City");
				toBeUpdated.setCity(sc.nextLine());
				Bank b = dao.updateBankDetails(toBeUpdated);
				if (b != null)
					System.out.println("Bank Details Updated");
				else
					System.out.println("Error in Changing Bank Details");
				break;
			}
			case 4: {
				System.out.println("Enter the Bank Contact");
				toBeUpdated.setContact(sc.nextLong());
				Bank b = dao.updateBankDetails(toBeUpdated);
				if (b != null)
					System.out.println("Bank Details Updated");
				else
					System.out.println("Error in Changing Bank Details");
				break;
			}
			default: {
				System.out.println("Invalid Option");
				break;
			}
			}
		} else {
			System.out.println("No Such Bank ID");
			return;
		}
	}

	public void deleteBank(Scanner sc) {
		System.out.println("Enter the Bank Id you want to Delete");
		int id = sc.nextInt();
		Bank b = dao.findBankDetail(id);
		if (b != null) {
			Manager m = b.getManager();
			m.setBank(null);
			// if error change here
			b.setCustomers(null);
			b.setManager(null);
			b = dao.updateBankDetails(b);
			b = dao.deleteBankDetail(b.getId());
			if (b != null) {
				System.out.println("Bank Details Deleted Successfully");
			} else {
				System.out.println("Error in Deleting Bank Details");
			}
		}
	}

	public void getBankList() {
		List<Bank> bankList = dao.getAllBankList();
		if (bankList != null) {
			for (Bank b : bankList) {
//				System.out.println();
				System.out.println(b.getId() + " " + b.getBankName() + " " + b.getCity() + " " + b.getIfsc() + " "
						+ b.getContact());
			}
		}
	}

	public void AssignManagerToBank(Scanner sc) {
		System.out.println("Enter the Bank Id you want to Assing the Manager");
		int bid = sc.nextInt();
		Bank b = dao.findBankDetail(bid);
		if (b != null) {
			System.out.println("Want to Assing New Manager or Existing Manager");
			System.out.println("Enter the Choice");
			System.out.println("1 ----> Existing Manager");
			System.out.println("2 ----> New Manager");
			int op = sc.nextInt();
			switch (op) {
			case 1: {
				System.out.println("Enter the Manager Id");
				int id = sc.nextInt();
				Manager m = managerDao.findManagerDetails(id);
				if (m != null) {
					m.setBank(b);
					b.setManager(m);
					Bank updated = dao.updateBankDetails(b);
					if (updated != null) {
						System.out.println("Manager Assinged to Bank");
					}
				} else {
					System.out.println("Manager Not Found");
				}
				break;
			}
			case 2: {
				System.out.println("Enter the Manager Details");
				Manager m = new Manager();
				System.out.println("Enter the Manager Name");
				m.setName(sc.nextLine());
				System.out.println("Enter the Manager Contact");
				m.setContact(sc.nextLong());
				System.out.println("Enter the Manager City");
				m.setCity(sc.nextLine());
				m.setBank(b);
				Bank updated = dao.updateBankDetails(b);
				if (updated != null) {
					System.out.println("Manager Details Updated Successfully");
				}
				break;
			}

			default: {
				System.out.println("Invalid Option");
				break;
			}
			}
		}
	}

	public void updateManagerDetails(Scanner sc) {
		System.out.println("Enter the Manager Id you want to update");
		int mId = sc.nextInt();
		Manager finded = managerDao.findManagerDetails(mId);
		if (finded != null) {
			System.out.println("Enter the Manager Details to Update");
			System.out.println("1 ----> Manager Name");
			System.out.println("2 ----> Manager City");
			System.out.println("3 ----> Manager Contact");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter the Manager Name");
				finded.setName(sc.nextLine());
				finded = managerDao.updateManagerDetails(finded);

				break;
			}
			case 2: {
				System.out.println("Enter the Manager City");
				finded.setCity(sc.nextLine());
				finded = managerDao.updateManagerDetails(finded);
				break;
			}
			case 3: {
				System.out.println("Enter the Manager Contact");
				finded.setContact(sc.nextLong());
				finded = managerDao.updateManagerDetails(finded);
				break;
			}
			default: {
				System.out.println("Invalid Option");
			}
			}
			if (finded != null) {
				System.out.println("Manager Details Updated");
			} else {
				System.out.println("Error in Updating Manager Details");
			}
		}
	}

}
