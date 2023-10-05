package ir.maktab.springboot.service;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.exception.DuplicatePhoneNumberException;
import ir.maktab.springboot.exception.PhoneNumberNotFoundException;
import ir.maktab.springboot.model.student.CommonStudent;
import ir.maktab.springboot.model.student.Student;
import ir.maktab.springboot.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    private StudentService underTest;
    @Mock
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(repository);
    }

    @Test
    void itShouldThrowExceptionWhenPhoneNumberIsDuplicate() {
        String duplicatePhoneNumber = "phoneNumber";
        StudentRegisterDto studentRegisterDto = new StudentRegisterDto("name", duplicatePhoneNumber);
        given(repository.existsByPhoneNumber(duplicatePhoneNumber))
                .willReturn(true);

        Throwable caughtThrowable = catchThrowable(() -> underTest.register(studentRegisterDto));

        assertThat(caughtThrowable).isExactlyInstanceOf(DuplicatePhoneNumberException.class);
    }

    @Test
    void itShouldSaveStudentSuccessfully() {
        StudentRegisterDto studentRegisterDto = new StudentRegisterDto("name", "phoneNumber");

        underTest.register(studentRegisterDto);

        verify(repository).save(any(Student.class));
    }

    @Test
    void itShouldThrowExceptionWhenPhoneNumberNotFound() {
        Throwable caughtThrowable = catchThrowable(() -> underTest.findByPhoneNumber("phoneNumber"));
        assertThat(caughtThrowable).isExactlyInstanceOf(PhoneNumberNotFoundException.class);
    }

    @Test
    void itShouldFindStudentSuccessfully() {
        String phoneNumber = "phoneNumber";
        given(repository.findByPhoneNumber(phoneNumber))
                .willReturn(Optional.of(new CommonStudent("name", phoneNumber)));
        StudentDto actual = underTest.findByPhoneNumber(phoneNumber);
        assertThat(actual)
                .isNotNull()
                .matches(student -> phoneNumber.equals(student.phoneNumber()));
    }
}