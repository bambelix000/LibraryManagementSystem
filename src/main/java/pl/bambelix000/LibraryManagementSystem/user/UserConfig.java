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
            User test2 = new User(
                    "Bob",
                    "Bobikowski",
                    "Bobikowski@gmail.com",
                    "12345678901"
            );
            User test3 = new User(
                    "Mietek",
                    "Mietczyński",
                    "Mietkowski@gmail.com",
                    "12312312312"
            );


            repository.saveAll(List.of(test, test2, test3));
        };

    }
}
