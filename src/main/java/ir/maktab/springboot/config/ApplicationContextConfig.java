package ir.maktab.springboot.config;

import ir.maktab.springboot.repository.InMemoryStudentRepository;
import ir.maktab.springboot.repository.JdbcStudentRepository;
import ir.maktab.springboot.repository.StudentRepository;
import ir.maktab.springboot.service.LogEnabledStudentService;
import ir.maktab.springboot.service.StudentService;
import ir.maktab.springboot.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @ConditionalOnProperty( prefix = "application.configs.repository",
                            name = "mode",
                            havingValue = "in-memory")
    StudentRepository inMemoryStudentRepository() {
        return new InMemoryStudentRepository();
    }

    @Bean
    @ConditionalOnProperty( prefix = "application.configs.repository",
                            name = "mode",
                            havingValue = "jdbc")
    StudentRepository jdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcStudentRepository(jdbcTemplate);
    }

//    @Configuration
//    @EnableJpaRepositories(basePackages = "ir.maktab.springboot.repository")
//    @ConditionalOnProperty(prefix = "application.configs.repository", name = "mode", havingValue = "jpa")
//    public static class JpaConfig {
//        @Bean
//        StudentRepository jpaStudentRepository(StudentJpaRepository jpaRepository) {
//            return new JpaStudentRepository(jpaRepository);
//        }
//    }

    @Bean
    StudentService studentService(StudentRepository repository,
                                  @Value("${application.configs.log.enable}") boolean isLogEnabled) {
        StudentService studentService = new StudentServiceImpl(repository);
        if (isLogEnabled) studentService = new LogEnabledStudentService(studentService);
        return studentService;
    }

//    @Bean
//    MessageService messageService(MessageSource messageSource) {
//        return new ResourceBundleMessageService(messageSource);
//    }
}


