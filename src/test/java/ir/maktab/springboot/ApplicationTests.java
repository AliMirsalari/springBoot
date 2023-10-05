package ir.maktab.springboot;

import ir.maktab.springboot.dto.StudentDto;
import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test", "production"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ApplicationTests extends AbstractTestcontainers {

	@Autowired
	private StudentService underTest;

	@Test
	void contextLoads() {
	}

	@Test
	void itShouldSaveStudentSuccessfully() {
		StudentRegisterDto studentRegisterDto = new StudentRegisterDto("name", "9123456789");

		underTest.register(studentRegisterDto);

		StudentDto student = underTest.findByPhoneNumber(studentRegisterDto.phoneNumber());

		assertThat(student)
				.extracting(StudentDto::phoneNumber)
				.isEqualTo(studentRegisterDto.phoneNumber());
	}
}
