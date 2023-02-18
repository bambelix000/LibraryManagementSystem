package pl.bambelix000.LibraryManagementSystem.booked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public void bookABook(@ModelAttribute Booked booked){
        bookedService.bookABook(booked);
    }

    @PostMapping("/delete")
    @DeleteMapping
    public void returnBook(@ModelAttribute Booked booked){
        bookedService.returnBook(booked);
    }

}
