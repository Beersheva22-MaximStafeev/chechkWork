package telran.checkWork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.checkWork.dto.MessageEmail;
import telran.checkWork.exceptions.BadRequestException;
import telran.checkWork.service.SenderService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceEmailTest {
	
	@Autowired
	SenderService senderService;
	
	@Test
	void emailTest() throws Exception {
		MessageEmail messageEmail = new MessageEmail();
		var field = MessageEmail.class.getSuperclass().getDeclaredField("messageType");
		field.setAccessible(true);
		field.set(messageEmail, "email");
		assertEquals(MessageEmail.class, senderService.getMessageClass("email"));
		assertTrue(senderService.serviceExists("email"));
//		assertThrowsExactly(BadRequestException.class, () -> senderService.serviceExists(null))
	}
}
