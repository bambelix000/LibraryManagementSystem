package pl.bambelix000.LibraryManagementSystem.bookedbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bookedbooks")
public class BookedBooksController {

    private final BookedBooksService bookedBooksService;

    @Autowired
    public BookedBooksController(BookedBooksService bookedBooksService) {
        this.bookedBooksService = bookedBooksService;
    }

    @GetMapping
    public List<BookedBooks> getBooked(){
        return bookedBooksService.getBooked();
    }
}
