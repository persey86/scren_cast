package com.example.sweater;

import com.example.sweater.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// будет подключаться к тестовым пропертям по указанному имени
@TestPropertySource("/application-test.properties")
// будут выполняться SQL-скрипты перед каждым накатом тестов из указанного файла, можно ставить над классами и методами
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql","/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//будет задавать пользователя к текущему контексту, можно ставить над классами и методами
@WithUserDetails("admin")
public class MessageControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MessageController controller;


	@Test
	public void mainPageTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("admin"));
	}

	@Test
	public void messageListTest() throws Exception {
		this.mockMvc.perform(get("/main"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//div[@id='message-list']/span").nodeCount(4));

	}

	@Test
	public void filterMessageTest() throws Exception {
		this.mockMvc.perform(get("/main").param("filter", "my-tag"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//div[@id='message-list']/span").nodeCount(2))
				.andExpect(xpath("//div[@id='message-list']/span[@data-id=1]").exists())
				.andExpect(xpath("//div[@id='message-list']/span[@data-id=3]").exists());
	}

	@Test
	public void addMessageToListTest() throws Exception {
		MockHttpServletRequestBuilder multipart = multipart("/main")
				.file("file", "123".getBytes())
				.param("text", "fifth")
				.param("tag", "new one")
				.with(csrf());
		this.mockMvc.perform(multipart)
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//div[@id='message-list']/span").nodeCount(5))
				.andExpect(xpath("//div[@id='message-list']/span[@data-id=10]").exists())
				.andExpect(xpath("//div[@id='message-list']/span[@data-id=10]").string("fifth"));
//				.andExpect(xpath("//div[@id='message-list']/i").string("fifth"));
	}
}
