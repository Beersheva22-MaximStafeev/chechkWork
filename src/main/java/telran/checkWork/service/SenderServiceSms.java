package telran.checkWork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import telran.checkWork.dto.Message;
import telran.checkWork.dto.MessageSms;

@Service
public class SenderServiceSms implements Sender {

	private static final Logger LOG = LoggerFactory.getLogger(SenderServiceSms.class);

	@Override
	public String sendServiceMessage(Message message) {
		MessageSms msg = (MessageSms) message;
		String result = String.format("sendServiceMessage SmsSender, sending message: %s", msg.toString());
		LOG.debug(result);
		return result;
	}

	@Override
	public String getMessageType() {
		return "sms";
	}

	@Override
	public Class<? extends Message> getMessageClass() {
		return MessageSms.class;
	}

}
