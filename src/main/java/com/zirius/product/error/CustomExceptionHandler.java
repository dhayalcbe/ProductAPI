package com.zirius.product.error;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String RESOURCE_ACCESS_EXCEPTION_OCCURED = "Resource access exception occured";
	private static final String EXCEPTION_OCCURED = "Exception occured";
	private static final String IO_EXCEPTION_OCCURED = "IOException occured";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<ErrorDetail> fieldErrors = result.getFieldErrors().stream().map(f -> {
			ErrorDetail ed = new ErrorDetail();
			ed.setDescription(f.getDefaultMessage());
			ed.setParameter(f.getField());
			ed.setCode(Integer.toString(status.value()));
			return ed;
		}).collect(Collectors.toList());
		
		ErrorDetails errors = ErrorDetails.builder().errorMessage(status.getReasonPhrase()).errorDetails(fieldErrors).build();
		return ResponseEntity.status(status).body(errors);
	}
	
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ErrorDetails> handleResourceAccessException(ResourceAccessException ex, WebRequest request) {
		ErrorDetails errors = ErrorDetails.builder().errorMessage(RESOURCE_ACCESS_EXCEPTION_OCCURED).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorDetails> handleAllExceptions(IOException ex, WebRequest request) {
		ErrorDetails errors = ErrorDetails.builder().errorMessage(IO_EXCEPTION_OCCURED).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errors = ErrorDetails.builder().errorMessage(EXCEPTION_OCCURED).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
	}
}
