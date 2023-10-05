package ir.maktab.springboot.repository;

import ir.maktab.springboot.AbstractTestcontainers;
import ir.maktab.springboot.model.student.CommonStudent;
import ir.maktab.springboot.model.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcStudentRepositoryTest extends AbstractTestcontainers {
    private StudentRepository underTest;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.execute("delete from student");
        underTest = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void itShouldExistsByPhoneNumber() {
        Student student = new CommonStudent("ali", "9123456789");
        underTest.save(student);

        boolean actual = underTest.existsByPhoneNumber(student.getPhoneNumber());

        assertThat(actual).isTrue();
    }

    @Test
    void itShouldNotExistsByPhoneNumber() {
        boolean actual = underTest.existsByPhoneNumber("9123456789");

        assertThat(actual).isFalse();
    }

    @Test
    void itShouldSaveStudentSuccessfully() {
        Student student = new CommonStudent("ali", "9123456789");
        underTest.save(student);

        Optional<Student> actual = underTest.findByPhoneNumber(student.getPhoneNumber());

        assertThat(actual)
                .isPresent()
                .get()
                .extracting(Student::getName)
                .isEqualTo(student.getName());
    }
}
