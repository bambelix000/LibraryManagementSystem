package pl.bambelix000.LibraryManagementSystem.bookedbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookedBooksRepository extends JpaRepository<BookedBooks, Long> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.booked_books (id, author, title, social_security_number) VALUES (:id, :author, :title, :ssn);", nativeQuery = true)
    void setBookedBooks(@Param("id") Integer id, @Param("author") String author, @Param("title") String title, @Param("ssn") String socialSecurityNumber);

    @Query(value = "SELECT author FROM public.booked_books WHERE social_security_number = :ssn", nativeQuery = true)
    String getAuthor(@Param("ssn") String socialSecurityNumber);

    @Query(value = "SELECT title FROM public.booked_books WHERE social_security_number = :ssn", nativeQuery = true)
    String getTitle(@Param("ssn") String socialSecurityNumber);



}
