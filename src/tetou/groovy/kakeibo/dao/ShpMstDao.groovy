package tetou.groovy.kakeibo.dao

import org.hibernate.Session
import org.hibernate.Transaction

import tetou.groovy.kakeibo.common.HibernateUtil
import tetou.groovy.kakeibo.entity.ShpMst

class ShpMstDao {
	
	boolean insert(ShpMst sm) throws Exception{
		Session session = getSession()
		Transaction transaction = session.beginTransaction()
		
		//既存データ確認
		boolean exists = false
		List<ShpMst> list = session.createCriteria(ShpMst.class).list()
		list.each { obj ->
			if (obj.shpName == sm.shpName
				&&obj.deleted=="0"){
				exists = true
			}
		}
		if (exists) return false
		
		//新規データ登録
		try {
			sm.id = list.size() + 1
			sm.insId = "insert"
			sm.insYmd = new Date()
			sm.updId = "insert"
			sm.updYmd = new Date()
			sm.deleted = "0"
			session.save(sm)
			transaction.commit()
		}
		catch (Exception e){
			e.printStackTrace()
			transaction.rollback()
			return false
		}
		finally {
			session.flush()
			session.close()
		}
		true
	}
	
	List<ShpMst> findByDeleted(del) throws Exception{
		//セッション取得
		Session session = getSession()
		
		List<ShpMst> smList = new ArrayList<ShpMst>()
		((List<ShpMst>)session.createCriteria(ShpMst.class).list()).each{ sm ->
			if (sm.deleted == del) smList.add(sm)
		}
		
		//セッション終了
		session.close()
		
		//結果返却
		smList
	}

	Session getSession(){
		HibernateUtil.getSessionFactory().openSession()
	}
}
