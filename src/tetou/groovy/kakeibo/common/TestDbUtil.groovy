package tetou.groovy.kakeibo.common

import java.sql.DriverManager

import com.mysql.jdbc.Connection

class TestDbUtil {
	//�q�N���X�ɂċ�̓I�Ɏw��
	protected String hostName
	protected int port
	protected String dbName
	protected String userName
	protected String passWord
	
	//�e�X�g�p�N���X��DB�𑀍삷�邽�߂̃��\�b�h
	protected Connection getConnection() throws Exception{
		Class.forName("org.gjt.mm.mysql.Driver")
		Connection connection=(Connection)DriverManager.
		getConnection("jdbc:mysql://"+hostName+":"+Integer.toString(port)+
				"/"+dbName+"?autoReconnect=true&amp;autoReconnectForPools=true&amp;useUnicode=true&amp;characterEncoding=Shift_JIS",
				userName,passWord)
		connection
	}
}
