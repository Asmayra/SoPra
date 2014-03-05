package org.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.model.Song;
import org.model.User;

/**
 * @author Philipp Kather, Mattias Schoenke, Tim Lange
 *
 */
public class DatabaseControl {

	private static StandardServiceRegistryBuilder serviceRegistryBuilder;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static DatabaseControl instance;
	
	private DatabaseControl(){
		System.out.println("initialize DataBase");
		initDatabaseConnection();
	}
	/**
	 * Singelton Method to access DataBase Methods 
	 * @return the Database
	 */
	public static DatabaseControl getInstance(){
		if (instance == null){
			instance = new DatabaseControl();
		}
		return instance;
	}
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void setSessionFactory(SessionFactory sessionFactory) {
		DatabaseControl.sessionFactory = sessionFactory;
	}
	/**
	 * initializes the Database Connection, creates
	 */
	private void initDatabaseConnection(){
		Configuration cfg = new Configuration();
	       
	    cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://ec2-54-197-241-97.compute-1.amazonaws.com:5432/d3u49np6d74s36?user=hplhjlrqhdygcn&password=m_4DPC_jRqEWK_Mf4r2yzMa5cD&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
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
			//session.persist(o);
			session.saveOrUpdate(o);
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
	
	/**
	 * select form database with given keyword	
	 * @param c Class of the Object
	 * @param property column name to be selected from
	 * @param keyword String with keyword
	 * @return List with results
	 */
	public List<?> queryForKeyword(Class c, String property, String keyword){
		session = sessionFactory.openSession();
		session.beginTransaction();

		//Query q = session.createQuery("SELECT firstname from User WHERE to_tsvector(username) @@ to_tsquery(:keyword)");;
		//q.setParameter("keyword", keyword);
		//List<?> results = q.list();
		Criteria crit = session.createCriteria(c);
		crit.add(Restrictions.ilike(property, "%"+keyword+"%"));
		List<?> results = crit.list();	
		session.getTransaction().commit();
		session.close();
		return results;
	}
	
	/**
	 * Aktualisiert ein persistiertes Objekt mit den neuen Werten aus update. Wenn das Objekt noch nicht persistiert
	 * wurde, wird es neu angelegt.
	 * !!!!KANN NICHT PRIMARY KEY ÄNDERN!!!!
	 * @param update Objekt von dem die neuen Werte gelesen werden
	 * @return aktualisiertes Objekt
	 */
	public Object update(Object update)
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Object result = session.merge(update);
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	/**
	 * Löscht das persistente Objekt, das zu diesem Objekt gehört. Gibt keine Fehler wenn man 
	 * nicht existente Daten löschen will.
	 * @param delete Objekt dessen persistente Version gelöscht werden soll
	 */
	public void delete(Object delete)
	{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(delete);
			session.getTransaction().commit();
			session.close();
	}
	
	/**
	 * Aktualisiert ein persistentes Objekt. Kann auch Primary ändern
	 * @param c Klasse des zu änderden Objektes
	 * @param id Primarykey des zu ändernden Objektes
	 * @param update Objekt mit allen neuen Werten
	 * @return Geändertes Objekt
	 */
	public Object updatePrimary(Class c, Serializable id, Object update)
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Object toUpdate = session.load(c, id);
		
		if(toUpdate == null)
		{
			return null;
		}
		
		session.delete(toUpdate);
		
		session.persist(update);

		session.getTransaction().commit();
		session.close();
		return update;
	}
	
}
