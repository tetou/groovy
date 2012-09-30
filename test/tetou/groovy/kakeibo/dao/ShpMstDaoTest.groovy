package tetou.groovy.kakeibo.dao

import java.io.FileInputStream

import org.dbunit.Assertion
import org.dbunit.database.DatabaseConnection
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ITable
import org.dbunit.dataset.filter.DefaultColumnFilter
import org.dbunit.dataset.xml.FlatXmlDataSet

import com.mysql.jdbc.Connection



import java.sql.DriverManager
import java.sql.SQLException

import org.dbunit.database.DatabaseConnection
import org.dbunit.database.IDatabaseConnection
import org.dbunit.database.QueryDataSet
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.xml.FlatXmlDataSet
import org.dbunit.operation.DatabaseOperation

import tetou.groovy.kakeibo.entity.ShpMst

import com.mysql.jdbc.Connection

class ShpMstDaoTest extends GroovyTestCase {
	
	private def hostName
	private def port
	private def dbName
	private def userName
	private def passWord
	
	private def file
	
	ShpMstDaoTest(){
		// TODO Auto-generated constructor stub
		this.hostName="api02.chocola.moe.hm"
		this.port=3306
		this.dbName="kakeibo"
		this.userName="myuser"
		this.passWord="mypass"
	}

	protected void setUp() throws Exception {
		
		IDatabaseConnection connection =null
		try{
			Connection conn=getConnection()
			connection = new DatabaseConnection(conn)
			
			//����̃o�b�N�A�b�v���擾
			QueryDataSet partialDataSet = new QueryDataSet(connection)
			partialDataSet.addTable("SHP_MST")
			file=File.createTempFile("shpmst",".xml")
			FlatXmlDataSet.write(partialDataSet,
					 new FileOutputStream(file))

			//�e�X�g�f�[�^�𓊓�����
			IDataSet dataSet = new FlatXmlDataSet( new FileInputStream("test/tetou/groovy/kakeibo/dao/shp_mst_test_data.xml"))
			DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet)

		}catch(Exception e){
			e.printStackTrace()
		}finally{
			try{
			if(connection!=null) connection.close()
			}catch(SQLException e){}
		}
	}

	protected void tearDown() throws Exception {
		IDatabaseConnection connection =null
		try{
		    Connection conn=getConnection()
		    connection = new DatabaseConnection(conn)

		    IDataSet dataSet = new FlatXmlDataSet(file)
		    DatabaseOperation.CLEAN_INSERT.execute(connection,dataSet)
		    
		}catch(Exception e){
		    e.printStackTrace()
		}finally{
		    try{
			if(connection!=null) connection.close()
		    }catch(SQLException e){}
		}
	}

	public void testFindByDeleted() {

		def smDao = new ShpMstDao()
		
		//���폜���X�g�擾
		def result = smDao.findByDeleted("0")
		//�T�C�Y����v���Ă��邩
		assertEquals(4, result.size())
		//�I�����ꂽ�}�X�^���͍����Ă��邩
		def idList = new ArrayList<Integer>()
		result.each { obj ->
			idList.add(obj.id)
		}
		assert idList.sort() == [1,2,3,5]
		
		//�폜���X�g�擾
		result = smDao.findByDeleted("1")
		//�T�C�Y����v���Ă��邩
		assertEquals(1, result.size())
		//�I�����ꂽ�}�X�^���͍����Ă��邩
		idList = []
		result.each { obj ->
			idList.add(obj.id)
		}
		assert idList.sort() == [4]
	}
	
	public void testInsert1(){
		
		def smDao = new ShpMstDao()
		
		//�V�K�I�u�W�F�N�g�������o�^
		def sm = new ShpMst(shpName:"�t�@�~���[�}�[�g�~�b�h�^�E��21F")
		assert smDao.insert(sm) == true
		
		//���s���ʂ����؂���
		IDatabaseConnection connection=null
		try{
			// shp_mst�e�[�u���̏�Ԃ��m�F
			String[] ignoreKeys = ["INS_YMD","UPD_YMD"]
			Connection conn=getConnection()
			connection  =new DatabaseConnection(conn)
			IDataSet databaseDataSet = connection.createDataSet()
			ITable actualTable = DefaultColumnFilter.excludedColumnsTable(databaseDataSet.getTable("shp_mst"), ignoreKeys)
			
			// ���҂����f�[�^���擾
			IDataSet expectedDataSet = new FlatXmlDataSet(new FileInputStream("test/tetou/groovy/kakeibo/dao/shp_mst_insert_data.xml"))
			ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(expectedDataSet.getTable("shp_mst"), ignoreKeys)
			
			// ��r����
			Assertion.assertEquals(expectedTable, actualTable)
		}finally{
			if(connection!=null) connection.close()
		}
	}
	
	public void testInsert2(){
		
		def smDao = new ShpMstDao()
		
		//�V�K�I�u�W�F�N�g�������o�^�����ɓo�^�ς݂̏ꍇ
		def sm = new ShpMst(shpName:"�Z�u���C���u���Z�{�؃~�b�h�^�E���X")
		assert smDao.insert(sm) == false
		
		//���s���ʂ����؂��遦�ω��͂Ȃ��͂�
		IDatabaseConnection connection=null
		try{
			// shp_mst�e�[�u���̏�Ԃ��m�F
			String[] ignoreKeys = ["INS_YMD","UPD_YMD"]
			Connection conn=getConnection()
			connection  =new DatabaseConnection(conn)
			IDataSet databaseDataSet = connection.createDataSet()
			ITable actualTable = DefaultColumnFilter.excludedColumnsTable(databaseDataSet.getTable("shp_mst"), ignoreKeys)
			
			// ���҂����f�[�^���擾
			IDataSet expectedDataSet = new FlatXmlDataSet(new FileInputStream("test/tetou/groovy/kakeibo/dao/shp_mst_test_data.xml"))
			ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(expectedDataSet.getTable("shp_mst"), ignoreKeys)
			
			// ��r����
			Assertion.assertEquals(expectedTable, actualTable)
		}finally{
			if(connection!=null) connection.close()
		}
	}
	
	Connection getConnection() throws Exception{
		Class.forName("org.gjt.mm.mysql.Driver")
		Connection connection=(Connection)DriverManager.getConnection("jdbc:mysql://"+hostName+":"+Integer.toString(port)+"/"+dbName+"?autoReconnect=true&amp;autoReconnectForPools=true&amp;useUnicode=true&amp;characterEncoding=Shift_JIS",userName,passWord)
		connection
	}

}
