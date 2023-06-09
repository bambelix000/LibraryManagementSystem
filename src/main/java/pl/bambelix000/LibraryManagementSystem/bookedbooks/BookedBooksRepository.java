package pl.bambelix000.LibraryManagementSystem.bookedbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookedBooksRepository extends JpaRepository<BookedBooks, Long> {


    Optional<BookedBooks> findBySocialSecurityNumber(String ssn);
    Optional<BookedBooks> findByTitle(String title);
    Optional<BookedBooks> findByAuthor(String author);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.booked_books (id, surname,  author, title, social_security_number) VALUES (:id,:surname, :author, :title, :ssn);", nativeQuery = true)
    void setBookedBooks(@Param("id") Long id,
                        @Param("surname") String surname,
                        @Param("author") String author,
                        @Param("title") String title,
                        @Param("ssn") String socialSecurityNumber);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.booked_books WHERE id = :id",nativeQuery = true)
    void deleteBook(@Param("id") Long id);

    @Query(value = "SELECT MIN(id) FROM BookedBooks WHERE title = :title and author = :author and social_security_number = :ssn")
    Long selectMinId(@Param("title")String title,
                     @Param("author") String author,
                     @Param("ssn") String socialSecurityNumber);


    @Query(value = "SELECT MAX(id) FROM public.booked_books", nativeQuery = true)
    Long getI();




}
