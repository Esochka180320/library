package com.example.demo.controllerTest;

import com.example.demo.controller.BookController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class BookControllerTest {
  @Autowired
  BookController bookController;

  @MockBean
  BookService bookService;

  private final String name = "December";
  private final String name2 = "November";
  Book book1 = new Book(1, name, true);
  Book book2 = new Book(1, name2, true);


  @Test
  void addBook_Pass() {
    Mockito.doReturn(book1)
            .when(bookService)
            .addBook(book1);
    assertEquals(bookController.addBook(book1).getBody(), book1);
  }

  @Test
  void addBook_Fail() {
    Mockito.doReturn(null)
            .when(bookService)
            .addBook(book1);
    assertNull(bookController.addBook(book1).getBody());
  }

  @Test
  void findBook_Pass() {
    Mockito.doReturn(book1)
            .when(bookService)
            .findBook(1);
    assertEquals(bookController.findBook(1).getBody(), book1);
  }

  @Test
  void findBook_Fail() {
    Mockito.doReturn(null)
            .when(bookService)
            .findBook(1);
    assertNull(bookController.findBook(1).getBody());
  }

  @Test
  void updateBook_Pass() {
    Mockito.doReturn(book2)
            .when(bookService)
            .updateBook(book2);
    assertEquals(bookController.updateBook(book2).getBody(), book2);
  }

  @Test
  void updateBook_Fail() {
    Mockito.doReturn(null)
            .when(bookService)
            .updateBook(book2);
    assertNull(bookController.updateBook(book2).getBody());
  }

  @Test
  void deleteBook() {
    assertEquals(bookController.deleteBook(1).getBody(), "delete");
  }
}
