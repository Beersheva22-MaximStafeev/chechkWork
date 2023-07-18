package telran.checkWork.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import telran.checkWork.dto.Message;
import telran.checkWork.exceptions.BadRequestException;

@Service
public class SenderService {

	private static final Logger LOG = LoggerFactory.getLogger(SenderService.class);

	private Map<String, Sender> allServices;
	
	SenderService(List<Sender> senderServices) {
		allServices = senderServices.stream().collect(Collectors.toMap(Sender::getMessageType, Function.identity()));
		LOG.debug("all registered services: {}", senderServices.toString());
	}

	public String sendMessage(Message message) {
		Sender service = allServices.get(message.getMessageType());
		if (service == null) {
			throw new BadRequestException("no such service " + message.getMessageType());
		}
		return service.sendMessage(message);
	}
	
	public boolean serviceExists(String messageType) {
		return allServices.get(messageType) != null;
	}

	public List<String> getAllMessageTypes() {
		return allServices.keySet().stream().toList();
	}

	public Class<? extends Message> getMessageClass(String messageType) {
		Class<? extends Message> messageClass = null;
		Sender service = allServices.get(messageType);
		if (service != null) {
			messageClass = service.getMessageClass(); 
		}
		return messageClass;
	}
	
}
