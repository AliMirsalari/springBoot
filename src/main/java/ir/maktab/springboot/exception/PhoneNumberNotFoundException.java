package ir.maktab.springboot.exception;

public class PhoneNumberNotFoundException extends RuntimeException {
    public PhoneNumberNotFoundException(String phoneNumber) {
        super("Phone number [%s] not found!".formatted(phoneNumber));
    }
}
