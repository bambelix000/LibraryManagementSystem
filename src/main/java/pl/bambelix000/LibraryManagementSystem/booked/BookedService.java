package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.bambelix000.LibraryManagementSystem.book.Book;
import pl.bambelix000.LibraryManagementSystem.book.BookRepository;
import pl.bambelix000.LibraryManagementSystem.user.User;
import pl.bambelix000.LibraryManagementSystem.user.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class BookedService {
    @Autowired
    private final BookedRepository bookedRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookedService(BookedRepository bookedRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookedRepository = bookedRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Booked> getBooked(){
        return bookedRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void bookABook(Booked booked){
        Optional<User> userOptional = userRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<Book> bookOptional = bookRepository.findByTitle(booked.getTitle());
        Optional<Book> authorOptional = bookRepository.findByAuthor(booked.getAuthor());
        Optional<Booked> bookedUserOptional = bookedRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());


        if(userOptional.isEmpty()) throw new IllegalStateException("This user doesn't exists");
        else if(authorOptional.isEmpty()) throw new IllegalStateException("This author doesn't written this book");
        else if(bookOptional.isEmpty()) throw new IllegalStateException("This book doesn't exists");
        else if(bookedUserOptional.isPresent()){
            bookedRepository.updateBooks(booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());
            bookedRepository.borrowBook(booked.getTitle());
        }
            else{
            booked.setSurname(bookedRepository.getSurname(booked.getSocialSecurityNumber()));
            bookedRepository.save(booked);
            bookedRepository.borrowBook(booked.getTitle());
        }
    }

    public void returnBook(Booked booked){
        Optional<Booked> ssnOptional = bookedRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<Booked> titleOptional = bookedRepository.findByTitle(booked.getTitle());
        Optional<Booked> authorOptional = bookedRepository.findByAuthor(booked.getAuthor());

        if(ssnOptional.isEmpty()) throw new IllegalStateException("This user doesn't exists");
        else if(titleOptional.isEmpty() || authorOptional.isEmpty()) throw new IllegalStateException("This user haven't borrowed this book");
        else{

            bookedRepository.deleteBook(/*booked.getAuthor(), */booked.getTitle(), booked.getSocialSecurityNumber());
            bookedRepository.returnBook(booked.getTitle());
        }

    }



}
