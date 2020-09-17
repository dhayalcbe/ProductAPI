//package com.zirius.product.error;
//
//import javax.annotation.Nullable;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.zalando.problem.DefaultProblem;
//import org.zalando.problem.Problem;
//import org.zalando.problem.ProblemBuilder;
//import org.zalando.problem.spring.web.advice.ProblemHandling;
//import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
//import org.zalando.problem.violations.ConstraintViolationProblem;
//
//@ControllerAdvice
//public class ExceptionTranslator implements ProblemHandling {
//
//	private static final String MESSAGE_KEY = "message";
//	private static final String VIOLATIONS_KEY = "violations";
//	private static final String ERR_VALIDATION = "error.validation";
//	private static final String ERR_KEY = "error";
//
//	@Override
//	public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
//		if (null == entity) {
//			return entity;
//		}
//		Problem problem = entity.getBody();
//		if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
//			return entity;
//		}
//		ProblemBuilder builder = Problem.builder();
//		ErrorDetails errors = ErrorDetails.builder().errorMessage(problem.getDetail()).build();
//		builder.with(ERR_KEY, errors);
//		return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
//	}
//
//	//IOException
//}
