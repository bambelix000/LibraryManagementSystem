package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.bambelix000.LibraryManagementSystem.user.User;
import pl.bambelix000.LibraryManagementSystem.user.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class BookedService {
    @Autowired
    private final BookedRepository bookedRepository;
    private final UserRepository userRepository;
    private final User user;

    @Autowired
    public BookedService(BookedRepository bookedRepository, UserRepository userRepository, User user) {
        this.bookedRepository = bookedRepository;
        this.userRepository = userRepository;
        this.user = user;
    }

    public List<Booked> getBooked(){
        return bookedRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void bookABook(Booked booked){
        Optional<User> userOptional = userRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());

        if(userOptional.isPresent()){
            bookedRepository.save(booked);

        }else{
            throw new IllegalStateException("User with this social security number doesn't exists");
        }
    }
}
