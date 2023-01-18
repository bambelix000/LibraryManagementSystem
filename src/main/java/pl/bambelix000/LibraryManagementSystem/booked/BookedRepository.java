package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface BookedRepository extends JpaRepository<Booked, Long> {

    Optional<Booked> findBySocialSecurityNumber(String ssn);


    @Query(value = "SELECT surname FROM public.user WHERE social_security_number = :ssn", nativeQuery = true)
    String getSurname(@Param("ssn") String ssn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.book SET booked = booked + 1 WHERE title = :title AND author = :author", nativeQuery = true)
    void borrowBook(@Param("title") String title, @Param("author") String author);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.booked SET author = :author, title = :title WHERE social_security_number = :ssn", nativeQuery = true)
    void updateAuthorAndTitle(@Param("author") String author, @Param("title") String title, @Param("ssn") String socialSecurityNumber);

}
