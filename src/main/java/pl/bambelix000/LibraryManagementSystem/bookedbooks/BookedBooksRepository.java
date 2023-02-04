package pl.bambelix000.LibraryManagementSystem.bookedbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookedBooksRepository extends JpaRepository<BookedBooks, Long> {


    Optional<BookedBooks> findBySocialSecurityNumber(String ssn);
    Optional<BookedBooks> findByTitle(String title);
    Optional<BookedBooks> findByAuthor(String author);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.booked_books (id, author, title, social_security_number) VALUES (:id, :author, :title, :ssn);", nativeQuery = true)
    void setBookedBooks(@Param("id") Long id, @Param("author") String author, @Param("title") String title, @Param("ssn") String socialSecurityNumber);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.booked_books WHERE id = :id",nativeQuery = true)
    void deleteBook(@Param("id") Long id);

    @Transactional
    @Query(value = "SELECT MIN(id) FROM public.booked_books WHERE title = :title AND author = :author AND social_security_number = :ssn", nativeQuery = true)
    Long selectMinId(@Param("title")String title, @Param("author") String author, @Param("ssn") String socialSecurityNumber);

    @Query(value = "SELECT MAX(id) FROM public.booked_books", nativeQuery = true)
    Long getI();

    @Query(value = "SELECT social_security_number FROM public.booked_books WHERE id = :id", nativeQuery = true)
    String getSsn(@Param("id")Long id);

    @Query(value = "SELECT author FROM public.booked_books WHERE social_security_number = :ssn", nativeQuery = true)
    String getAuthor(@Param("ssn") String socialSecurityNumber);

    @Query(value = "SELECT title FROM public.booked_books WHERE social_security_number = :ssn", nativeQuery = true)
    String getTitle(@Param("ssn") String socialSecurityNumber);




}
