package org.control;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DatabaseController {

	private static StandardServiceRegistryBuilder serviceRegistryBuilder;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static DatabaseController instance;
	
	private DatabaseController(){
		initDatabaseConnection();
	}
	/**
	 * Singelton Method to access DataBase Methods 
	 * @return the Database
	 */
	public static DatabaseController getInstance(){
		if (instance == null){
			instance = new DatabaseController();
		}
		return instance;
	}
	
	/**
	 * initializes the Database Connection, creates
	 */
	private void initDatabaseConnection(){
		Configuration cfg = new Configuration();
	       
	    cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://ec2-54-197-238-239.compute-1.amazonaws.com:5432/dc38385afumsn0?user=yuxvjihrqgsqoz&password=DslK6nwLkoC8PINe7iouQ1F4EU&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
	    cfg.configure("/META-INF/hibernate.xml");
	    cfg.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
	    serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	    serviceRegistryBuilder.applySettings(cfg.getProperties());
	     
	    ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	     
		System.out.println("config loaded");
	     //serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	    //sf = cfg.buildSessionFactory();
	    System.out.println("build session");
	    
	    System.out.println("opening...");
 		session = sessionFactory.openSession();
 		System.out.println("opended");
 		System.out.println(session.isConnected());
 		//this.session.buildLockRequest(LockOptions.NONE);
 		//this.createDatabaseIfEmpty();
	}
	
	/**
	 * Saves the Object o in the DataBase
	 * @param o 
	 */
	private void save(Object o){
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(o);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * loads an Object from the Database. 
	 * @param c Class of the Object
	 * @param id Id of the Object
	 * @return Object (needs to be casted)
	 */
	private Object load(Class c,Serializable id){
		session = sessionFactory.openSession();
		session.beginTransaction();
		Object o = session.get(c, id);
		session.getTransaction().commit();
		session.close();
		return o;
	}
}
