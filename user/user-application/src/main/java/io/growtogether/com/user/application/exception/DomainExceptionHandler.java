package io.growtogether.com.user.application.exception;

import io.growtogether.com.finbill.user.domain.exception.ClientNotFoundException;
import io.growtogether.com.finbill.user.domain.exception.EmailAlreadyExistsException;
import io.growtogether.com.finbill.user.domain.exception.InvalidEmailException;
import io.growtogether.com.finbill.user.domain.exception.InvalidPhoneNumberException;
import io.growtogether.com.user.application.controller.ClientController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ClientController.class)
@Slf4j
public class DomainExceptionHandler {

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ProblemDetail> handleInvalidPhoneNumber(InvalidPhoneNumberException ex) {
        log.warn("Invalid phone number: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), "Invalid Phone Number");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ProblemDetail> handleInvalidEmail(InvalidEmailException ex) {
        log.warn("Invalid email: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), "Invalid Email");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        log.warn("Email already exists: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.CONFLICT, ex.getMessage(), "Email Already Exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleClientNotFound(ClientNotFoundException ex) {
        log.warn("Client not found: {}", ex.getMessage());
        var problemDetail = buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), "Client Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    private ProblemDetail buildProblemDetail(HttpStatus httpStatus, String message, String title) {
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setTitle(title);
        return problemDetail;
    }
}

