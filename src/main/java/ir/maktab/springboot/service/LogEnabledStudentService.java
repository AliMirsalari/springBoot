package ir.maktab.springboot.service;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.exception.DuplicatePhoneNumberException;
import ir.maktab.springboot.exception.PhoneNumberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class LogEnabledStudentService implements StudentService {
    private final StudentService service;

    @Override
    public void register(StudentRegisterDto studentDto) {
        log.info("registration request {}", studentDto);
        try {
            service.register(studentDto);
        } catch (DuplicatePhoneNumberException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDto findByPhoneNumber(String phoneNumber) {
        try {
            return service.findByPhoneNumber(phoneNumber);
        } catch (PhoneNumberNotFoundException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }
}
