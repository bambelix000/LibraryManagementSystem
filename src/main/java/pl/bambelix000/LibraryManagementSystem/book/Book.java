package pl.bambelix000.LibraryManagementSystem.book;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Integer amount;

    @Transient
    private Integer amountAvailable;


    private Integer booked;

    public Book(String author,
                String title,
                Integer amount,
                Integer booked) {
        this.author = author;
        this.title = title;
        this.amount = amount;
        this.booked = booked;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }

    public Integer getAmountAvailable() {
        return amount - getBooked();
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }
}
