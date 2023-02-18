package pl.bambelix000.LibraryManagementSystem.bookedbooks;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "booked_books")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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
    private String surname;
    private String title;
    private String author;

    public BookedBooks(String socialSecurityNumber,String surname, String title, String author) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.surname= surname;
        this.title = title;
        this.author = author;
    }
}
