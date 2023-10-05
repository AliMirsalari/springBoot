package ir.maktab.springboot.exception;

public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException(String phoneNumber) {
        super("Phone number [%s] is already existed!".formatted(phoneNumber));
    }
}
