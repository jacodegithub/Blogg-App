package com.springboot.blog.app;

import com.springboot.blog.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringBootBlogAppApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Test
	void contextLoads() {
		System.out.println(passwordEncoder.encode("irteqha"));
		System.out.println(passwordEncoder.encode("waqhar"));
		System.out.println(passwordEncoder.encode("sharjeel"));
		System.out.println(passwordEncoder.encode("tauqeer"));
		System.out.println(passwordEncoder.encode("muzakir"));
		System.out.println(passwordEncoder.encode("tanzila"));
		System.out.println(passwordEncoder.encode("shahenaz"));
	}
}
