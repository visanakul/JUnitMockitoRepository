package com.mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mockito.resources.HelloResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestWithMockitoDemoApplicationTests {

	private MockMvc mockMvc;
	@InjectMocks
	private HelloResource helloResource;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();
	}

	@Test
	public void testSayHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello World!!!"));
	}

}
