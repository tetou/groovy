package tetou.groovy.kakeibo.common

import org.hibernate.HibernateException
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

class HibernateUtil {
	private static final SessionFactory sessionFactory=buildSessionFactory()
	private static final String configFilePath="./properties/hibernate.cfg.xml"
	
	private static SessionFactory buildSessionFactory(){
		try {
			new Configuration().configure(configFilePath).buildSessionFactory()
		}
		catch (HibernateException e){
			throw new RuntimeException("Configuration Problem: "+e.getMessage(),e)
		}
	}
	
	static SessionFactory getSessionFactory(){
		sessionFactory
	}
}
