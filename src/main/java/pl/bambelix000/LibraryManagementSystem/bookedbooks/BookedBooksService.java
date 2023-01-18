package pl.bambelix000.LibraryManagementSystem.bookedbooks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookedBooksService {

    @Autowired
    private final BookedBooksRepository bookedBooksRepository;

    @Autowired
    public BookedBooksService(BookedBooksRepository bookedBooksRepository) {
        this.bookedBooksRepository = bookedBooksRepository;
    }



}
