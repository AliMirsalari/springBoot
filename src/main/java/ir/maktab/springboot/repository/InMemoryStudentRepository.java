package ir.maktab.springboot.repository;

import ir.maktab.springboot.model.student.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<String, Student> database = new HashMap<>();

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return database.containsKey(phoneNumber);
    }

    @Override
    public void save(Student student) {
        database.put(student.getPhoneNumber(), student);
    }

    @Override
    public Optional<Student> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(database.get(phoneNumber));
    }
}
