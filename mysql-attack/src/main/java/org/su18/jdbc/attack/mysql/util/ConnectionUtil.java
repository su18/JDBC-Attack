package org.su18.jdbc.attack.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author su18
 */
public class ConnectionUtil {

	/**
	 * 建立 JDBC 连接
	 *
	 * @param driverName    驱动名称
	 * @param connectionUrl 连接URL
	 * @param username      用户名
	 * @param password      密码
	 */
	public static Connection getJDBCConnection(String driverName, String connectionUrl, String username, String password)
			throws Exception {

		// 装载驱动
		Class.forName(driverName);

		// getConnection 触发漏洞
		return DriverManager.getConnection(connectionUrl, username, password);
	}


	public static Connection getJDBCConnection(String driverName, String connectionUrl) throws Exception {
		// 装载驱动
		Class.forName(driverName);

		// getConnection 触发漏洞
		return DriverManager.getConnection(connectionUrl, "root", "123456");
	}


}
