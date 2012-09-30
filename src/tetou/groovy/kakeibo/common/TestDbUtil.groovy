package tetou.groovy.kakeibo.common

import java.sql.DriverManager

import com.mysql.jdbc.Connection

class TestDbUtil {
	//子クラスにて具体的に指定
	protected String hostName
	protected int port
	protected String dbName
	protected String userName
	protected String passWord
	
	//テスト用クラスがDBを操作するためのメソッド
	protected Connection getConnection() throws Exception{
		Class.forName("org.gjt.mm.mysql.Driver")
		Connection connection=(Connection)DriverManager.
		getConnection("jdbc:mysql://"+hostName+":"+Integer.toString(port)+
				"/"+dbName+"?autoReconnect=true&amp;autoReconnectForPools=true&amp;useUnicode=true&amp;characterEncoding=Shift_JIS",
				userName,passWord)
		connection
	}
}
