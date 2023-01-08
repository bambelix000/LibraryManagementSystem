package pl.bambelix000.LibraryManagementSystem.book;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class BookConfig {


    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> repository.saveAll(List.of());

    }
}
