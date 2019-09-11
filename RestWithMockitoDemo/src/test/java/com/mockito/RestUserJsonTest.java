package com.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class RestUserJsonTest {

	private MockMvc mockMvc;
	@InjectMocks
	private HelloResource helloResource;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();
	}

	@Test
	public void testJsonLoginSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/login").accept(MediaType.APPLICATION_JSON_VALUE).param("uname", "admin"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.uname", is("admin")))
				.andExpect(jsonPath("$.pass", is("admin123"))).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void testXMLLoginSuccess() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/login").accept(MediaType.APPLICATION_XML_VALUE).param("uname", "admin"))
				.andExpect(status().isOk()).andExpect(xpath("/user/uname").string("admin"))
				.andExpect(xpath("/user/pass").string("admin123")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test(expected = NestedServletException.class)
	@ExceptionHandler(value = RuntimeException.class)
	public void testJsonLoginFail() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/login").accept(MediaType.APPLICATION_JSON_VALUE).param("uname", "abc"))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test(expected = NestedServletException.class)
	@ExceptionHandler(value = RuntimeException.class)
	public void testXMLLoginFail() throws Exception {
		MvcResult result = mockMvc.perform(post("/login").accept(MediaType.APPLICATION_XML_VALUE).param("uname", "abc"))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
