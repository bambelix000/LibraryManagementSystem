package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookedConfig {

    @Bean
    CommandLineRunner lineRunner(BookedRepository bookedRepository){
        return args->{

        };
    }
}
