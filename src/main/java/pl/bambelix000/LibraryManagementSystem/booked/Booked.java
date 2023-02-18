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

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String socialSecurityNumber;

    @Setter
    @Getter
    private String title;

    @Getter
    @Setter
    private String author;

    public Booked(String socialSecurityNumber,
                  String title,
                  String author) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.title = title;
        this.author = author;
    }


}
