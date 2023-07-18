package telran.checkWork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.annotation.JsonRootName;

import telran.checkWork.dto.Message;
import telran.checkWork.dto.MessageTcp;

@Service
public class SenderServiceTcp implements Sender {

	private static final Logger LOG = LoggerFactory.getLogger(SenderServiceTcp.class);

	@Override
	public String getMessageType() {
		return "tcp";
	}

	@Override
	public Class<? extends Message> getMessageClass() {
		return MessageTcp.class;
	}

	@Override
	public String sendServiceMessage(Message message) {
		MessageTcp msg = (MessageTcp) message;
		String result = String.format("sendServiceMessage TcpSender, sending message: %s", msg.toString());
		LOG.debug(result);
		return result;
	}


}
