package courses.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import courses.hibernate.util.HibernateUtil;
import courses.hibernate.vo.Account;

public class AccountDAOHibernate implements AccountDAOInterface {

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account account to be persisted
	 */
	@Override
	public Account createAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Long accountId = (Long) session.save(account); 
		tx.commit();
		session.close();
		return getAccount(accountId);
	}

	/**
	 * Update an account
	 * 
	 * @param account account to be created
	 */
	@Override
	public void updateAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
		session.close();
	}

	/**
	 * Delete account from data store
	 * 
	 * @param account account to be deleted
	 */
	@Override
	public void deleteAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(account);
		tx.commit();
		session.close();
	}

	/**
	 * Retrieve an account from the data store
	 * 
	 * @param accountId identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	@Override
	public Account getAccount(long accountId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Account account = (Account) session.get(Account.class, accountId);
		tx.commit();
		session.close();
		return account;
	}

}
