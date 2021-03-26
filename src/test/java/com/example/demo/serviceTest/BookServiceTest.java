package com.example.demo.serviceTest;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    private final String name = "December";
    private final String name2 = "November";
    Book book1 = new Book(1, name, true);
    Book book2 = new Book(1, name2, true);


    @Test
    void addBook_Pass() {
        Mockito.when(bookRepository.save(book1)).thenReturn(book1);
        assertEquals(bookService.addBook(book1), book1);
    }

    @Test
    void addBook_Fail() {
        Mockito.when(bookRepository.save(book1)).thenReturn(null);
        assertNull(bookService.addBook(book1));
    }

    @Test
    void findBook_Pass() {
        Mockito.when(bookRepository.findBookById(1)).thenReturn(book1);
        assertEquals(bookService.findBook(1), book1);
    }

    @Test
    void findBook_Fail() {
        Mockito.when(bookRepository.findBookById(4)).thenReturn(null);
        assertNull(bookService.findBook(4));
    }

    @Test
    void updateBook_Pass() {
        Mockito.when(bookRepository.findBookById(book2.getId())).thenReturn(book1);
        Mockito.when(bookRepository.save(book1)).thenReturn(book2);
        assertEquals(bookService.updateBook(book2), book2);
    }

    @Test
    void updateBook_Fail() {
        Mockito.when(bookRepository.findBookById(1)).thenReturn(null);
        assertEquals(bookService.updateBook(book2), null);
    }
}
