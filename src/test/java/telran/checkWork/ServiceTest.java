package telran.checkWork;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.checkWork.dto.Message;
import telran.checkWork.exceptions.BadRequestException;
import telran.checkWork.service.SenderService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	SenderService senderService;
	
	@Test
	void serviceFailTest() throws Exception {
		var unknownMessageType = senderService.getAllMessageTypes().stream().reduce(String::concat).orElse("");
		Message message = new Message();
		var field = Message.class.getDeclaredField("messageType");
		field.setAccessible(true);
		field.set(message, unknownMessageType);
		assertThrowsExactly(BadRequestException.class, () -> senderService.sendMessage(message));
		assertFalse(senderService.serviceExists(unknownMessageType));
		assertNull(senderService.getMessageClass(unknownMessageType));
	}

}
