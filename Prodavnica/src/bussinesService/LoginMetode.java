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
	private Session session = sf.openSession();
	
	public boolean daLiPostojiUser(String userName) {
		
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
	
	

}
