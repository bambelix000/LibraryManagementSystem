package pl.bambelix000.LibraryManagementSystem.bookedbooks;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "booked_books")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookedBooks {
    @Id
    @SequenceGenerator(
            name = "bookedBooks_sequence",
            sequenceName = "bookedBooks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookedBooks_sequence"
    )
    private Long id;
    private String socialSecurityNumber;
    private String title;
    private String author;

    public BookedBooks(String socialSecurityNumber, String title, String author) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
