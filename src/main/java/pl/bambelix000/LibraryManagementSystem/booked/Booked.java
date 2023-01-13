package pl.bambelix000.LibraryManagementSystem.booked;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "booked")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booked {
    @Id
    @SequenceGenerator(
            name = "booked_sequence",
            sequenceName = "booked_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booked_sequence"
    )
    @Getter
    @Setter
    private Long id;

    private String surname;

    @Setter
    @Getter
    private String socialSecurityNumber;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String author;

    public Booked(String socialSecurityNumber,
                  String title,
                  String author) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.title = title;
        this.author = author;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
