package ir.maktab.springboot.repository;

import ir.maktab.springboot.model.student.CommonStudent;
import ir.maktab.springboot.model.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        String sql = """
                select  count(*)
                from student
                where phone_number = ?
                """;
        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                phoneNumber
        );
        return count != null && count == 1;
    }

    @Override
    public void save(Student student) {
        String sql = """
                insert into student(name, phone_number)
                values (?, ?)
                """;
        jdbcTemplate.update(
                sql,
                student.getName(),
                student.getPhoneNumber()
        );
    }

    @Override
    public Optional<Student> findByPhoneNumber(String phoneNumber) {
        String sql = """
                select name, phone_number
                from student
                where phone_number = ?
                """;
        return jdbcTemplate.query(
                        sql,
                        (rs, rowNum) -> new CommonStudent(
                                rs.getString("name"),
                                rs.getString("phone_number")
                        ),
                        phoneNumber)
                .stream()
                .map(Student.class::cast)
                .findFirst();
    }
}
