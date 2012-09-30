package tetou.groovy.kakeibo.dao

import org.hibernate.Session
import org.hibernate.Transaction

import tetou.groovy.kakeibo.common.HibernateUtil
import tetou.groovy.kakeibo.entity.ShpMst

class ShpMstDao {
	
	boolean insert(ShpMst sm) throws Exception{
		Session session = getSession()
		Transaction transaction = session.beginTransaction()
		
		//�����f�[�^�m�F
		boolean exists = false
		List<ShpMst> list = session.createCriteria(ShpMst.class).list()
		list.each { obj ->
			if (obj.shpName == sm.shpName
				&&obj.deleted=="0"){
				exists = true
			}
		}
		if (exists) return false
		
		//�V�K�f�[�^�o�^
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
		//�Z�b�V�����擾
		Session session = getSession()
		
		List<ShpMst> smList = new ArrayList<ShpMst>()
		((List<ShpMst>)session.createCriteria(ShpMst.class).list()).each{ sm ->
			if (sm.deleted == del) smList.add(sm)
		}
		
		//�Z�b�V�����I��
		session.close()
		
		//���ʕԋp
		smList
	}

	Session getSession(){
		HibernateUtil.getSessionFactory().openSession()
	}
}
