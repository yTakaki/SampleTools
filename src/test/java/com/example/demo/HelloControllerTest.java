package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class HelloControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	@WithMockUser
	void helloへのGETメソッドでhelloworldビューに接続するテスト() throws Exception {
		this.mock.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(view().name("helloworld"));
	}
}
