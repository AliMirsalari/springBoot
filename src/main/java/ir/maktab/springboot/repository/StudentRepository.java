package ir.maktab.springboot.repository;

import ir.maktab.springboot.model.student.Student;

import java.util.Optional;

public interface StudentRepository {
    boolean existsByPhoneNumber(String phoneNumber);

    void save(Student student);

    Optional<Student> findByPhoneNumber(String phoneNumber);
}
