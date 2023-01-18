package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.bambelix000.LibraryManagementSystem.book.Book;
import pl.bambelix000.LibraryManagementSystem.book.BookRepository;
import pl.bambelix000.LibraryManagementSystem.bookedbooks.BookedBooksRepository;
import pl.bambelix000.LibraryManagementSystem.user.User;
import pl.bambelix000.LibraryManagementSystem.user.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class BookedService {
    public Integer i = 1;

    @Autowired
    private final BookedRepository bookedRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookedBooksRepository bookedBooksRepository;

    @Autowired
    public BookedService(BookedRepository bookedRepository, BookRepository bookRepository, UserRepository userRepository, BookedBooksRepository bookedBooksRepository) {
        this.bookedRepository = bookedRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookedBooksRepository = bookedBooksRepository;
    }

    public List<Booked> getBooked(){
        return bookedRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void bookABook(Booked booked){
        Optional<User> isUserPresent = userRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<Book> isTitlePresent = bookRepository.findByTitle(booked.getTitle());
        Optional<Booked> hasUserAlreadyBorrowBook = bookedRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<Book> isAuthorPresent = bookRepository.findByAuthor(booked.getAuthor());



        if(isUserPresent.isEmpty()) throw new IllegalStateException("This user doesn't exists");
        else if(isTitlePresent.isEmpty()) throw new IllegalStateException("This book doesn't exists");
        else if(isAuthorPresent.isEmpty()) throw new IllegalStateException("This author doesn't written this book");
        else if(hasUserAlreadyBorrowBook.isPresent()){

            bookedBooksRepository.setBookedBooks(i, booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());


//            bookedRepository.updateAuthorAndTitle(bookedBooksRepository.getAuthor(booked.getSocialSecurityNumber()),
//                                                  bookedBooksRepository.getTitle(booked.getSocialSecurityNumber()),
//                                                  booked.getSocialSecurityNumber());


            i += 1;
        }else{
            bookedBooksRepository.setBookedBooks(i, booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());

            booked.setSurname(bookedRepository.getSurname(booked.getSocialSecurityNumber()));
            booked.setSocialSecurityNumber(booked.getSocialSecurityNumber());
            booked.setAuthor(bookedBooksRepository.getAuthor(booked.getSocialSecurityNumber()));
            booked.setTitle(bookedBooksRepository.getTitle(booked.getSocialSecurityNumber()));


            bookedRepository.save(booked);





            i += 1;
        }

    }


}
