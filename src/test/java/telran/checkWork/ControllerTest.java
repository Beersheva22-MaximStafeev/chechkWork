package telran.checkWork;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.checkWork.dto.Message;
import telran.checkWork.dto.MessageEmail;
import telran.checkWork.service.SenderService;

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	SenderService senderService;
	
	@Test
	void getAllMessageTypes() throws Exception {
		List<String> correctAnswerList = Arrays.asList("one", "two", "Three");
		String correctAnswerString = mapper.writeValueAsString(correctAnswerList);
		when(senderService.getAllMessageTypes()).thenReturn(correctAnswerList);
		assertEquals(correctAnswerString, mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString()
				);
	}
	
	@Test
	void sendMessageEmail() throws Exception {
		MessageEmail message = new MessageEmail("email", "text", "aa@bb");
		String resString = "ok " + message;
		String messageJsonString = mapper.writeValueAsString(message);
		
		when(senderService.serviceExists(message.getMessageType())).thenReturn(true);
		// not working properly
//		when(senderService.getMessageClass(message.getMessageType())).thenReturn(message.getClass());
		doReturn(message.getClass()).when(senderService).getMessageClass(message.getMessageType());
		when(senderService.sendMessage(any(Message.class))).thenReturn(resString);
		assertEquals(resString, mockMvc
				.perform(post("/").content(messageJsonString))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString());
	}
}
