package telran.checkWork.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MessageSms extends Message {

	@Pattern(regexp = "\\+[\\d\\s\\(\\)\\-]+")
	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	public String phoneNumber;
	
	@Override
	public String toString() {
		return super.toString() + String.format(", phoneNumber: %s", phoneNumber);
	}
}
