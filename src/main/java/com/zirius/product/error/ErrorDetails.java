package com.zirius.product.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
		
	private String errorMessage;
	
	private List<ErrorDetail> errorDetails;

}
