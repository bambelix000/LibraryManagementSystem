package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.bambelix000.LibraryManagementSystem.book.Book;
import pl.bambelix000.LibraryManagementSystem.book.BookRepository;
import pl.bambelix000.LibraryManagementSystem.bookedbooks.BookedBooks;
import pl.bambelix000.LibraryManagementSystem.bookedbooks.BookedBooksRepository;
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

        boolean isEnable = bookedRepository.amount(booked.getTitle(), booked.getAuthor()) - bookedRepository.booked(booked.getTitle(), booked.getAuthor()) > 0;

       // PROBLEM Z QUERY

       // Optional<Book> isAuthorPresent = bookRepository.findByAuthor(booked.getAuthor());

         if(isUserPresent.isEmpty()) throw new IllegalStateException("This book doesn't exists");
         else if(isTitlePresent.isEmpty() ) throw new IllegalStateException(booked.getTitle() + "This book doesn't exists");
       // else if(isAuthorPresent.isEmpty()) throw new IllegalStateException("This author doesn't written this book");
        else if(hasUserAlreadyBorrowBook.isPresent() && isEnable){

            Long i = bookedBooksRepository.getI() + 1;

            bookedBooksRepository.setBookedBooks(i, booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());

            bookedRepository.borrowBook(booked.getTitle(), booked.getAuthor());

        }else if (hasUserAlreadyBorrowBook.isEmpty() && isEnable){
            Long i = bookedBooksRepository.getI() + 1;

            bookedBooksRepository.setBookedBooks(i, booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());


            booked.setSurname(bookedRepository.getSurname(booked.getSocialSecurityNumber()));
            booked.setSocialSecurityNumber(booked.getSocialSecurityNumber());
            booked.setAuthor("booked_books table");
            booked.setTitle("booked_books table");

            bookedRepository.borrowBook(booked.getTitle(), booked.getAuthor());

            bookedRepository.save(booked);

        }else throw new IllegalStateException("This book isn't enable already");
    }



    public void returnBook(Booked booked){
        Optional<BookedBooks> isUserPresent = bookedBooksRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<BookedBooks> isAuthorPresent = bookedBooksRepository.findByAuthor(booked.getAuthor());
        Optional<BookedBooks> isTitlePresent = bookedBooksRepository.findByTitle(booked.getTitle());

        if(isUserPresent.isPresent() && isAuthorPresent.isPresent() && isTitlePresent.isPresent()){
            bookedRepository.returnBook(booked.getTitle(),booked.getAuthor());


            // query nie zwraca JEDNEJ wartości nwm czemu
            Long id = bookedBooksRepository.selectMinId(booked.getTitle(),booked.getAuthor(), booked.getSocialSecurityNumber());


        }
    }





}
