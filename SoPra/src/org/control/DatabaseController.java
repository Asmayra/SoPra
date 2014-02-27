package org.control;

import org.hibernate.cfg.Configuration;


public class DatabaseController {

	static StandardServiceRegistryBuilder serviceRegistryBuilder;
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
	    sf = cfg.buildSessionFactory(serviceRegistry);
	    //sf = cfg.buildSessionFactory();
	    System.out.println("build session");
	    
	    System.out.println("opening...");
 		session = sf.openSession();
 		System.out.println("opended");
 		System.out.println(session.isConnected());
 		//this.session.buildLockRequest(LockOptions.NONE);
 		//this.createDatabaseIfEmpty();
	}
}
