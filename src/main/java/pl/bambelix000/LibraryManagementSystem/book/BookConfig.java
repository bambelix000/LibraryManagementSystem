package pl.bambelix000.LibraryManagementSystem.book;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> {
            Book test = new Book(
                    "Mirek",
                    "Test",
                    5,
                    1
            );
            Book test1 = new Book(
                    "Mietek",
                    "Test2",
                    3,
                    0
            );
            repository.saveAll(List.of(test, test1));
        };

    }
}
