package factories;


import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import models.com.Event;

public class SessionFactoryBuilder {

	private static SessionFactory sessionFactory=null;
	
	
	
	public static SessionFactory getSessionFactory() {

		if(sessionFactory==null) {
			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Event.class).buildSessionFactory();
		}
		return sessionFactory;
		
	}
	
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
			
		}
	}
}
