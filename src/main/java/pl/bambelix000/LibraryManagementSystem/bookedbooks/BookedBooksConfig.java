package pl.bambelix000.LibraryManagementSystem.bookedbooks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookedBooksConfig {

    @Bean
    CommandLineRunner runner(BookedBooksRepository repo){
        return args->{
            BookedBooks test = new BookedBooks(
                    "21372115918",
                    "Maro",
                    "Test",
                    "Mirek"
            );
            repo.saveAll(List.of(test));
        };
    }
}
