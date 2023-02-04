package pl.bambelix000.LibraryManagementSystem.bookedbooks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bambelix000.LibraryManagementSystem.booked.BookedRepository;

@Service
public class BookedBooksService {

    @Autowired
    private final BookedBooksRepository bookedBooksRepository;
    private final BookedRepository bookedRepository;

    @Autowired
    public BookedBooksService(BookedBooksRepository bookedBooksRepository,
                              BookedRepository bookedRepository) {
        this.bookedBooksRepository = bookedBooksRepository;
        this.bookedRepository = bookedRepository;
    }


}
