package org.su18.h2attack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 使用 h2 console 进行测试
@SpringBootApplication
public class H2AttackApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2AttackApplication.class, args);
	}

}
