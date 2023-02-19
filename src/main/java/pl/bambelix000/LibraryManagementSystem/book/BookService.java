package pl.bambelix000.LibraryManagementSystem.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
       return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
   }

   public void addNewBook(Book book){
       Optional<Book>authorOptional = bookRepository.findByAuthorAndTitle(book.getAuthor(), book.getTitle());
       book.setBooked(0);

       if(authorOptional.isPresent()){
           bookRepository.updateAmount(book.getAmount(), book.getTitle(), book.getAuthor());
       }else{
           bookRepository.save(book);
       }
   }
}
