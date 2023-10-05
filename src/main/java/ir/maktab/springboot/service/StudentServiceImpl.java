package ir.maktab.springboot.service;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.exception.DuplicatePhoneNumberException;
import ir.maktab.springboot.exception.PhoneNumberNotFoundException;
import ir.maktab.springboot.model.student.CommonStudent;
import ir.maktab.springboot.model.student.Student;
import ir.maktab.springboot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    public final StudentRepository repository;

    @Override
    public void register(StudentRegisterDto studentDto) {
        if (repository.existsByPhoneNumber(studentDto.phoneNumber()))
            throw new DuplicatePhoneNumberException(studentDto.phoneNumber());
        Student student = new CommonStudent(studentDto.name(), studentDto.phoneNumber());
        repository.save(student);
    }

    @Override
    public StudentDto findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .map(student -> new StudentDto(student.getName(), student.getPhoneNumber()))
                .orElseThrow(() -> new PhoneNumberNotFoundException(phoneNumber));
    }
}
