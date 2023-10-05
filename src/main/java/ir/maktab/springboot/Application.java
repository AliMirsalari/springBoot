package ir.maktab.springboot;

import ir.maktab.springboot.dto.StudentRegisterDto;
import ir.maktab.springboot.repository.InMemoryStudentRepository;
import ir.maktab.springboot.service.LogEnabledStudentService;
import ir.maktab.springboot.service.StudentService;
import ir.maktab.springboot.service.StudentServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner (StudentService studentService){
		return args -> {
			studentService.register(new StudentRegisterDto("abcd","abcd"));
			System.out.println(studentService.findByPhoneNumber("abcd"));
		};
	}

}
