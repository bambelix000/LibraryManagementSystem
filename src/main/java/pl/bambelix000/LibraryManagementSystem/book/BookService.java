package pl.bambelix000.LibraryManagementSystem.book;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
       return bookRepository.findAll();
   }




   public void addNewBook(Book book){
       Optional<Book>bookOptional = bookRepository.findByTitle(book.getTitle());

       if(bookOptional.isPresent()){

          bookRepository.updateAmount(book.getAmount(), book.getTitle());

       }else{
           bookRepository.save(book);
       }
   }
}
