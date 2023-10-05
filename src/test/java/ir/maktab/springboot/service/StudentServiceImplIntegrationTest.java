package ir.maktab.springboot.service;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.repository.InMemoryStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.assertThat;


class StudentServiceImplIntegrationTest {

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(new InMemoryStudentRepository());
    }

    @Test
    void itShouldSaveStudentSuccessfully() {
        StudentRegisterDto studentRegisterDto = new StudentRegisterDto("name", "phoneNumber");

        underTest.register(studentRegisterDto);

        StudentDto student = underTest.findByPhoneNumber(studentRegisterDto.phoneNumber());

        assertThat(student)
                .extracting(StudentDto::phoneNumber)
                .isEqualTo(studentRegisterDto.phoneNumber());
    }
}