package telran.checkWork.exceptions;

import jakarta.validation.ConstraintViolationException;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@RestControllerAdvice
public class GlobalExceptionsHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionsHandler.class);
	
	@ExceptionHandler({
		BadRequestException.class, 
		ConstraintViolationException.class,  
		MethodArgumentNotValidException.class, 
		IllegalAccessException.class
		,MismatchedInputException.class
		,HttpMessageNotReadableException.class
		,IllegalArgumentException.class
		,		RuntimeException.class
		})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	String getCustomExceptionText(Exception e) {
		LOG.error("Server has thrown exception with message: {}", e.getMessage());
//		return "This is error";
		return e.getMessage();
	}
	
	@ExceptionHandler({
//		RuntimeException.class, 
		Throwable.class, 
		Exception.class
		})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	String getStadardExceptionTest(RuntimeException e) {
		LOG.error("Server has thrown exception with message: {}", e.getMessage());
		return e.getMessage();
	}
}
