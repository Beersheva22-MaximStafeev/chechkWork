package telran.checkWork.service;

import telran.checkWork.dto.Message;

public interface Sender {

	default String sendMessage(Message message) {
		return getMessageType() + " sent with response: [" + sendServiceMessage(message) + "]";
	}
	String sendServiceMessage(Message message);
	String getMessageType();
	Class<? extends Message> getMessageClass();
}
