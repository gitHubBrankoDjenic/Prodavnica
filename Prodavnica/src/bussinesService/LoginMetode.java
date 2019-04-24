package bussinesService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;

public class LoginMetode {
	
	private SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	
	public boolean daLiPostojiUser(String userName) {
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		try {
			Query query = session.createQuery("FROM User WHERE userName = :Name");
			query.setParameter("Name", userName);
			
			List<User> listOfUsers = new ArrayList<User>();
			
			listOfUsers = query.getResultList();
			
			if (listOfUsers .size()==1 ){
				session.getTransaction().commit();
				System.out.println("User name postoji");
				return true;
				
			}else {
				session.getTransaction().commit();
				System.out.println("User name ne postoji");
				return false;
			}
			
		}catch (Exception e) {
			
			session.getTransaction().rollback();
			System.out.println("Neuspela transakcija");
			return false;
			
		}finally {
			session.close();
		}
		
	}
	
	public boolean daLiJeDobarPassword(String userName, String password) {
		
		Session session = sf.openSession();
		boolean b = true;
		
		session.beginTransaction();
		
		try {
			Query query = session.createQuery("Select password FROM User WHERE userName = :Name");
			query.setParameter("Name", userName);
			
			List<String> listOfPasswords = new ArrayList<String>();
			
			listOfPasswords = query.getResultList();
			
			for (String s:listOfPasswords) {
				if (s.equals(password)) {
					b = true;
				}else {
					b = false;
				}
			}
			session.getTransaction().commit();
			return b;		
		}catch (Exception e) {
			
			session.getTransaction().rollback();
			System.out.println("Neuspela transakcija");
			return false;
			
		}finally {
			session.close();
		}
		
	}

}
