package ro.sda.book_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.sda.book_app.model.Book;
import ro.sda.book_app.service.interfaces.BookService;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Void> save(@RequestBody @Validated Book book){
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/book/{idFromPath}")
    public ResponseEntity<Book> findById(@PathVariable("idFromPath") long id){

        Book result =  bookService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                        .body(result);

    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }
}
