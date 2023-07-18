package telran.checkWork.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MessageEmail extends Message {
	
	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	@Email
	public String emailAddress;
	
	public MessageEmail() {
		super();
	}
	
	public MessageEmail(@NotNull(message = Settings.NOT_NULL_MESSAGE) @NotEmpty String messageType,
			@NotNull(message = Settings.NOT_NULL_MESSAGE) @NotEmpty String text,
			@NotNull(message = Settings.NOT_NULL_MESSAGE) @Email String emailAddress) {
		super(messageType, text);
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", emailAddress: %s", emailAddress);
	}
}
