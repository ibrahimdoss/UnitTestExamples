package com.UnitTest.unitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UnitTestApplicationTests {

	@Test
	void contextLoads() {

		HelloWorld helloWorld = new HelloWorld();
		Assertions.assertEquals("Hello World!", helloWorld.sayHello(), "Say Hello to World!");
	}

}
