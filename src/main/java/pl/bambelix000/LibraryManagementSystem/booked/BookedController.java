package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booked")
public class BookedController {

    private final BookedService bookedService;

    @Autowired
    public BookedController(BookedService bookedService) {
        this.bookedService = bookedService;
    }

    @GetMapping
    public List<Booked> getBooked(){
        return bookedService.getBooked();
    }

    @PostMapping
    public void bookABook(@RequestBody Booked booked){
        bookedService.bookABook(booked);
    }

    @DeleteMapping
    public void returnBook(@RequestBody Booked booked){
        bookedService.returnBook(booked);
    }

}
