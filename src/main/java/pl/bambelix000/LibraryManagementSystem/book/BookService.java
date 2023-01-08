package pl.bambelix000.LibraryManagementSystem.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
            String dbUrl = "jdbc:postgresql://localhost:5432/LibraryManagementSystem";
            String dbUser = "postgres";
            String dbPassword = "Bartekpuma1";


           try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
               PreparedStatement statement = connection.prepareStatement("UPDATE public.book SET amount = amount + ? WHERE title = ?;")) {


               statement.setInt(1, book.getAmount());
               statement.setString(2, book.getTitle());
               statement.executeUpdate();

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }else{
           bookRepository.save(book);
       }
   }
}
