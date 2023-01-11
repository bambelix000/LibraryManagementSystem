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

    private String fullName;

    @Setter
    private String socialSecurityNumber;

    @Setter
    private String titleAuthor;

    public Booked(String socialSecurityNumber,
                  String titleAuthor) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.titleAuthor = titleAuthor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }


    public String getTitleAuthor() {
        return titleAuthor;
    }
}
