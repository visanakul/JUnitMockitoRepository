package com.mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import com.mockito.resources.HelloResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestUserXMLTest {

	@InjectMocks
	private HelloResource helloResources;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(helloResources).build();
	}

	@Test
	public void testUserLoginSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/login").param("uname", "admin").accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().isOk()).andExpect(xpath("/user/uname").string("admin"))
				.andExpect(xpath("/user/pass").string("admin123")).andReturn();
		System.out.println("Response : \n" + result.getResponse().getContentAsString());
	}

	@Test(expected = NestedServletException.class)
	@ExceptionHandler(RuntimeException.class)
	public void testUserLoginFail() throws Exception {
		MvcResult result = mockMvc.perform(post("/login").accept(MediaType.APPLICATION_XML_VALUE).param("uname", "abc"))
				.andExpect(status().isOk()).andExpect(xpath("/user/uname").string("admin"))
				.andExpect(xpath("/user/pass").string("admin123")).andDo(print()).andReturn();
		System.out.println("Response : \n" + result.getResponse().getContentAsString());
	}
}
