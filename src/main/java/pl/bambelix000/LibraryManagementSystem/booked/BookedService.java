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
        Optional<Booked> hasUserAlreadyBorrowBook = bookedRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<Book> authorAndTitle = bookRepository.findByAuthorAndTitle(booked.getAuthor(), booked.getTitle());

        boolean isEnable = bookedRepository.amount(booked.getTitle(), booked.getAuthor()) - bookedRepository.booked(booked.getTitle(), booked.getAuthor()) > 0;

        if(authorAndTitle.isPresent() &&  isEnable && isUserPresent.isPresent() && hasUserAlreadyBorrowBook.isEmpty()){
            Long i = bookedBooksRepository.getI() + 1;

            bookedRepository.borrowBook(booked.getTitle(), booked.getAuthor());

            bookedBooksRepository.setBookedBooks(i,bookedRepository.getSurname(booked.getSocialSecurityNumber()), booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());

            booked.setSurname(bookedRepository.getSurname(booked.getSocialSecurityNumber()));
            booked.setSocialSecurityNumber(booked.getSocialSecurityNumber());
            booked.setAuthor("booked_books table");
            booked.setTitle("booked_books table");

            bookedRepository.save(booked);

        }else if(authorAndTitle.isPresent() &&  isEnable && isUserPresent.isPresent() && hasUserAlreadyBorrowBook.isPresent()){
            Long i;
            if (bookedBooksRepository.getI() == null){
                i = 1L;
            }else{
                i = bookedBooksRepository.getI() + 1;
            }

            bookedRepository.borrowBook(booked.getTitle(), booked.getAuthor());

            bookedBooksRepository.setBookedBooks(i,bookedRepository.getSurname(booked.getSocialSecurityNumber()), booked.getAuthor(), booked.getTitle(), booked.getSocialSecurityNumber());

        }
        else if(authorAndTitle.isPresent() && isUserPresent.isEmpty())throw new IllegalStateException("This person doesn't exists");
        else if(authorAndTitle.isEmpty() && isUserPresent. isPresent()) throw new IllegalStateException("This book doesn't exists");
        else throw new IllegalStateException("This book isn't enable already");
    }


    public void returnBook(Booked booked){
        Optional<BookedBooks> isUserPresent = bookedBooksRepository.findBySocialSecurityNumber(booked.getSocialSecurityNumber());
        Optional<BookedBooks> isAuthorPresent = bookedBooksRepository.findByAuthor(booked.getAuthor());
        Optional<BookedBooks> isTitlePresent = bookedBooksRepository.findByTitle(booked.getTitle());

        if(isUserPresent.isPresent() && isAuthorPresent.isPresent() && isTitlePresent.isPresent()){
            bookedRepository.returnBook(booked.getTitle(),booked.getAuthor());

            // query nie zwraca JEDNEJ wartości nwm czemu
            bookedBooksRepository.deleteBook(bookedBooksRepository.selectMinId(booked.getTitle(),booked.getAuthor(), booked.getSocialSecurityNumber()));

        }
    }





}
