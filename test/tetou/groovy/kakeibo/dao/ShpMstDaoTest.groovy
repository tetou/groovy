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
			
			//現状のバックアップを取得
			QueryDataSet partialDataSet = new QueryDataSet(connection)
			partialDataSet.addTable("SHP_MST")
			file=File.createTempFile("shpmst",".xml")
			FlatXmlDataSet.write(partialDataSet,
					 new FileOutputStream(file))

			//テストデータを投入する
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
		
		//未削除リスト取得
		def result = smDao.findByDeleted("0")
		//サイズが一致しているか
		assertEquals(4, result.size())
		//選択されたマスタ情報は合っているか
		def idList = new ArrayList<Integer>()
		result.each { obj ->
			idList.add(obj.id)
		}
		assert idList.sort() == [1,2,3,5]
		
		//削除リスト取得
		result = smDao.findByDeleted("1")
		//サイズが一致しているか
		assertEquals(1, result.size())
		//選択されたマスタ情報は合っているか
		idList = []
		result.each { obj ->
			idList.add(obj.id)
		}
		assert idList.sort() == [4]
	}
	
	public void testInsert1(){
		
		def smDao = new ShpMstDao()
		
		//新規オブジェクト生成＆登録
		def sm = new ShpMst(shpName:"ファミリーマートミッドタウン21F")
		assert smDao.insert(sm) == true
		
		//実行結果を検証する
		IDatabaseConnection connection=null
		try{
			// shp_mstテーブルの状態を確認
			String[] ignoreKeys = ["INS_YMD","UPD_YMD"]
			Connection conn=getConnection()
			connection  =new DatabaseConnection(conn)
			IDataSet databaseDataSet = connection.createDataSet()
			ITable actualTable = DefaultColumnFilter.excludedColumnsTable(databaseDataSet.getTable("shp_mst"), ignoreKeys)
			
			// 期待されるデータを取得
			IDataSet expectedDataSet = new FlatXmlDataSet(new FileInputStream("test/tetou/groovy/kakeibo/dao/shp_mst_insert_data.xml"))
			ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(expectedDataSet.getTable("shp_mst"), ignoreKeys)
			
			// 比較する
			Assertion.assertEquals(expectedTable, actualTable)
		}finally{
			if(connection!=null) connection.close()
		}
	}
	
	public void testInsert2(){
		
		def smDao = new ShpMstDao()
		
		//新規オブジェクト生成＆登録※既に登録済みの場合
		def sm = new ShpMst(shpName:"セブンイレブン六本木ミッドタウン店")
		assert smDao.insert(sm) == false
		
		//実行結果を検証する※変化はないはず
		IDatabaseConnection connection=null
		try{
			// shp_mstテーブルの状態を確認
			String[] ignoreKeys = ["INS_YMD","UPD_YMD"]
			Connection conn=getConnection()
			connection  =new DatabaseConnection(conn)
			IDataSet databaseDataSet = connection.createDataSet()
			ITable actualTable = DefaultColumnFilter.excludedColumnsTable(databaseDataSet.getTable("shp_mst"), ignoreKeys)
			
			// 期待されるデータを取得
			IDataSet expectedDataSet = new FlatXmlDataSet(new FileInputStream("test/tetou/groovy/kakeibo/dao/shp_mst_test_data.xml"))
			ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(expectedDataSet.getTable("shp_mst"), ignoreKeys)
			
			// 比較する
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
