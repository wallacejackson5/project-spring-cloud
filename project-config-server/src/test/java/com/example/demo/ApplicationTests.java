package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc = null;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.build();
	}

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(MockMvcBuilders.webAppContextSetup(this.wac).build());
	}

	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/project-config-server/configs/project-gateway-server.yml"))
				.andExpect(status().is2xxSuccessful());
	}

}
