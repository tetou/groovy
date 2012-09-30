package tetou.groovy.kakeibo.dao

import org.hibernate.Session

import tetou.groovy.kakeibo.common.HibernateUtil;

class DetailedStatementDao {
	
	
	Session getSession(){
		HibernateUtil.getSessionFactory().openSession()
	}
}
