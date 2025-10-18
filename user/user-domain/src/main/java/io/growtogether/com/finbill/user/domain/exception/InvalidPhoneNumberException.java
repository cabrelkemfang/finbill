package io.growtogether.com.finbill.user.domain.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String phoneNumber) {
        super("The phone number provided is invalid: " + phoneNumber);
    }
}
