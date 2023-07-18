package telran.checkWork.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import telran.checkWork.dto.*;
import telran.checkWork.exceptions.BadRequestException;
import telran.checkWork.service.SenderService;

@RestController
@Validated
@RequestMapping("/")
public class Controller {
	
	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	SenderService senderService;
	
	@Autowired
	Validator validator;
	
	@Autowired
	ObjectMapper mapper;
	
	@PostMapping
	public String sendMessage(@RequestBody String json) {
		LOG.trace("Receive request: {}", json);
		String res = "";
		try {
//			ObjectNode messageNode = mapper.readValue(json, ObjectNode.class);
			Message messageNode = mapper.readValue(json, Message.class);
			validate(messageNode);
//			JsonNode messageTypeNode = messageNode.get("messageType");
//			if (messageTypeNode == null) {
//				LOG.error("messageType property didn't sent");
//				throw new BadRequestException("messageType property didn't sent");
//			}
//			String messageType = messageTypeNode.textValue();
			String messageType = messageNode.getMessageType();
			if (!senderService.serviceExists(messageType)) {
				String error = String.format("Service %s doesn't exists", messageType);
				LOG.error(error);
				throw new BadRequestException(error);
			}
			LOG.debug("message type {}", messageType);
			Message message = mapper.readValue(json, senderService.getMessageClass(messageType));
			validate(message);
			res = senderService.sendMessage(message);
		} catch (JsonProcessingException e) {
			LOG.error(e.toString());
			throw new BadRequestException(e.getMessage());
		}
		LOG.debug(res);
		return res;
	}

	private void validate(Message message) {
		Set<ConstraintViolation<Message>> violations = validator.validate(message);
		if (!violations.isEmpty()) {
			throw new BadRequestException(violations.stream().map(el -> el.getPropertyPath() + " " + el.getMessage()).toList().toString());
		}
	}
	
	@GetMapping
	public List<String> getAllMessageTypes() {
		List<String> res = senderService.getAllMessageTypes();
		LOG.debug("receive GET getAllMessageTypes, result: {}", res);
		return res;
	}
}
