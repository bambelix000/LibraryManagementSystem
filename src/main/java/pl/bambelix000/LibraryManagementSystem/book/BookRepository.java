package pl.bambelix000.LibraryManagementSystem.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByAuthorAndTitle(String author, String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.book SET amount = amount + :amount WHERE title = :title and author = :author", nativeQuery = true)
    void updateAmount(@Param("amount") Integer amount,
                      @Param("title") String title,
                      @Param("author") String author);
}
