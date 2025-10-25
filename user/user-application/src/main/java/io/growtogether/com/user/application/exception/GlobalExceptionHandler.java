package io.growtogether.com.user.application.exception;

import io.growtogether.com.user.application.controller.ClientController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice(basePackageClasses = ClientController.class)
@Slf4j
public class GlobalExceptionHandler {

    private static final String INTERNAL_ERROR_MESSAGE = "An unexpected error occurred";

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoResourceFound(NoResourceFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), "Resource Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDenied(AccessDeniedException ex) {
        log.warn("Access denied: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.FORBIDDEN, "Access denied", "Access Denied");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        var problemDetail = buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE, "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationError(MethodArgumentNotValidException exception) {
        log.warn("Validation error occurred");
        var problemDetail = buildProblemDetail(HttpStatus.BAD_REQUEST, "Validation failed", "Validation Error");

        var validationErrorList = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        problemDetail.setProperty("errors", validationErrorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    private ProblemDetail buildProblemDetail(HttpStatus httpStatus, String message, String title) {
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setTitle(title);
        return problemDetail;
    }
}