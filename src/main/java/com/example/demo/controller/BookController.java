package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping  ("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book data) {
            return ResponseEntity.ok(bookService.addBook(data));
    }

    @GetMapping  ("/findBook")
    public ResponseEntity<?> findBook(@RequestParam Integer id) {
        return ResponseEntity.ok(bookService.findBook(id));
    }

    @PutMapping  ("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody Book data) {
        return ResponseEntity.ok(bookService.updateBook(data));
    }

    @DeleteMapping  ("/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("delete");
    }
}
