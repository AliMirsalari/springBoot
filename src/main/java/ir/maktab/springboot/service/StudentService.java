package ir.maktab.springboot.service;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;

public interface StudentService {
    void register(StudentRegisterDto studentDto);
    StudentDto findByPhoneNumber(String phoneNumber);
}
