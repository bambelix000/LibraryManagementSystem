package pl.bambelix000.LibraryManagementSystem.book;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
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
    @NonNull
    private Long id;
    private String author;
    private String title;
    private Integer amount;
    @Transient
    private Integer amountAvailable;
    @Transient
    private Integer booked = 0;




    public Book(String author,
                String title,
                Integer amount) {
        this.author = author;
        this.title = title;
        this.amount = amount;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountAvailable() {
        return amount - booked;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

}
