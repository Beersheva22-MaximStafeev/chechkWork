package telran.checkWork.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MessageTcp extends Message {

	@NotEmpty
	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	@Pattern(regexp = "\\d{1,3}(\\.\\d{1,3}){3}", message = "should be valid")
	public String ipAddress;
	
	@Min(1024)
	@Max(5000)
	@NotNull(message = Settings.NOT_NULL_MESSAGE)
	public int port;
	
	@Override
	public String toString() {
		return super.toString() + String.format(", port: %d, ipAddress: %s", port, ipAddress);
	}

}
