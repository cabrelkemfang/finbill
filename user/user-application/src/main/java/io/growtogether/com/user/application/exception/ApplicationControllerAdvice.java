package io.growtogether.com.user.application.exception;

import io.growtogether.com.finbill.user.domain.exception.ClientNotFoundException;
import io.growtogether.com.finbill.user.domain.exception.EmailAlreadyExistsException;
import io.growtogether.com.finbill.user.domain.exception.InvalidEmailException;
import io.growtogether.com.finbill.user.domain.exception.InvalidPhoneNumberException;
import io.growtogether.com.user.application.controller.ClientController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice(basePackageClasses = ClientController.class)
@RequiredArgsConstructor
@Slf4j
public class ApplicationControllerAdvice {

    private static final String DEFAULT_MESSAGE = "unhandled server message";

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ProblemDetail> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex) {
        log.warn("Bad Request Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Bad Request");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ProblemDetail> handleInvalidEmailException(InvalidEmailException ex) {
        log.warn("Bad Request Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Bad Request");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Bad Request Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Bad Request");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.warn("No Resource Found Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleClientNotFoundException(ClientNotFoundException ex) {
        log.warn("No Resource Found Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> globalExceptionHandler(Exception ex) {
        log.warn(" Global Exception: ", ex);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
        problemDetail.setTitle("Global Exception");

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationError(MethodArgumentNotValidException exception) {
        log.warn("Validation Error: {}", exception);
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Error");
        problemDetail.setTitle("Validation Error");

        var validationErrorList = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        problemDetail.setProperty("errors", validationErrorList);
        return ResponseEntity.of(problemDetail).build();
    }
}
