package telran.checkWork.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
 
public class Message {

	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	@NotEmpty
	private String messageType;
	
	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	@NotEmpty
	private String text;
	
	public Message() {}
	
	public Message(@NotNull(message = Settings.NOT_NULL_MESSAGE) @NotEmpty String messageType,
			@NotNull(message = Settings.NOT_NULL_MESSAGE) @NotEmpty String text) {
		this.messageType = messageType;
		this.text = text;
	}

	@Override
	public String toString() {
		return String.format("messageType: %s, text: %s", messageType, text);
	}
	
	public String getMessageType() {
		return messageType;
	}
	
	public String getText() {
		return text;
	}
	
}
