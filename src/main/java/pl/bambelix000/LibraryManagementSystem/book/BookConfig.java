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
            Book szczelcy = new Book(

                    "Barański Bartłomiej",
                    "Szczelcy",
                    5
            );
            Book wendrofcy = new Book(
                    "Barański Bartłomiej",
                    "Wendrofcy",
                    3
            );
            repository.saveAll(List.of(szczelcy, wendrofcy));
        };
    }


}
