package telran.checkWork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import telran.checkWork.dto.MessageEmail;
import telran.checkWork.dto.Message;

@Service
public class SenderServiceEmail implements Sender {

	private static final Logger LOG = LoggerFactory.getLogger(SenderServiceEmail.class);

	@Override
	public String sendServiceMessage(Message message) {
		MessageEmail msg = (MessageEmail) message;
		String result = String.format("sendServiceMessage EmailSender, sending message: %s", msg.toString());
		LOG.debug(result);
		return result;
	}

	@Override
	public String getMessageType() {
		return "email";
	}

	@Override
	public Class<? extends Message> getMessageClass() {
		return MessageEmail.class;
	}

}
