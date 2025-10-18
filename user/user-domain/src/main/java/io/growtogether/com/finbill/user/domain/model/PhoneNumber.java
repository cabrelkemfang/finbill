package io.growtogether.com.finbill.user.domain.model;

import io.growtogether.com.finbill.user.domain.exception.InvalidPhoneNumberException;

public record PhoneNumber(String number) {
    public PhoneNumber {
        if (number.isBlank() || !isValid(number)) {
            throw new InvalidPhoneNumberException(number);
        }
    }

    private boolean isValid(String number) {
        // Validates Mauritian phone numbers in local and international format
        return number != null && number.matches("^(?:\\+230)?(5\\d{7}|[246]\\d{6})$");
    }
}
