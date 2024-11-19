package edu.pnu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHello() throws Exception {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("Hello : 둘리", result);
	}

}
