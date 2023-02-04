package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookedConfig {

    @Bean
    CommandLineRunner lineRunner(BookedRepository repo){
        return args->{
            Booked test = new Booked(
                    "21372115918",
                    "booked_books table",
                    "booked_books table"
            );

            repo.saveAll(List.of(test));
        };
    }
}
