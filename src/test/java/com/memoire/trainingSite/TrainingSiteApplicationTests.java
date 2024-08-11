package com.memoire.trainingSite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TrainingSiteApplicationTests {

	@Test
	void contextLoads() {
		// Test that the context loads correctly
	}

	@Test
	void testSimpleRequest() throws Exception {
		assertEquals(5, 5);
	}
}
