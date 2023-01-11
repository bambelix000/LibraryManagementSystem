package pl.bambelix000.LibraryManagementSystem.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandRunner(UserRepository repository){
        return args -> {
            User test = new User(
                    "Miro",
                    "Maro",
                    "test@gmail.com",
                    "21372115918"
            );

            repository.saveAll(List.of(test));
        };

    }
}
