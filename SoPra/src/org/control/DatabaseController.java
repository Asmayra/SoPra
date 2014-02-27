package org.control;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.model.User;


public class DatabaseController {

	private static StandardServiceRegistryBuilder serviceRegistryBuilder;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static DatabaseController instance;
	
	private DatabaseController(){
		System.out.println("initialize DataBase");
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
	    cfg.configure("/Hibernate.cfg.xml");
	    cfg.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
	    serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	    serviceRegistryBuilder.applySettings(cfg.getProperties());
	     
	    ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	     
		System.out.println("config loaded");
	     //serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	    //sf = cfg.buildSessionFactory();
	    //System.out.println("build session");
	    
	   // System.out.println("opening...");
 		//session = sessionFactory.openSession();
 		//System.out.println("opended");
 		//System.out.println(session.isConnected());
 		//this.session.buildLockRequest(LockOptions.NONE);
 		//this.createDatabaseIfEmpty();
	}
	
	/**
	 * Saves the Object o in the DataBase
	 * Example:
	 * 			DatabaseController.getInstance().save(user1);
	 * @param o 
	 * @throws IOException if riamray Key is already Taken
	 */
	public void save(Object o) throws IOException{
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(o);
			session.getTransaction().commit();
			session.close();
		}catch(org.hibernate.exception.ConstraintViolationException e){
			System.out.println("Primarykey taken!");
			throw new IOException();
		}
	
		
	}
	/**
	 * loads an Object from the Database. 
	 * Example: 
	 * 			user1 = (StandartUser) DatabaseController.getInstance().load(StandartUser.class, "User1");
	 * @param c Class of the Object
	 * @param id Id of the Object
	 * @return Object (needs to be casted)
	 */
	public Object load(Class c,Serializable id){
		session = sessionFactory.openSession();
		session.beginTransaction();
		Object obj = session.get(c, id);
		session.getTransaction().commit();
		session.close();
		return obj;
	}
}
