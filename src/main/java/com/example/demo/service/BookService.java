package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.CustomUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
  private final BookRepository bookRepository;
  private final CustomUserRepository customUserRepository;

  public BookService(BookRepository bookRepository, CustomUserRepository customUserRepository) {
    this.bookRepository = bookRepository;
    this.customUserRepository = customUserRepository;
  }

  @Transactional
  public Book addBook(Book book) {
    return bookRepository.save(book);
  }

  @Transactional
  public Book findBook(Integer id) {
    Book book = bookRepository.findBookById(id);
    if (!(book == null)) {
      return book;
    } else {
      return null;
    }
  }

  @Transactional
  public Book updateBook(Book book) {
    Book book1 = bookRepository.findBookById(book.getId());
    if (!(book1 == null)) {
      book1.setName(book.getName());
      return bookRepository.save(book1);
    } else {
      return null;
    }
  }

  @Transactional
  public void deleteBook(Integer id) {
    Book book = bookRepository.findBookById(id);
    if (!(book == null)) {
      CustomUser customUser = customUserRepository.findByBookListContains(book);
      if (customUser == null) {
        bookRepository.deleteById(id);
      } else {
        customUser.returnBook(book);
        bookRepository.deleteById(id);
      }
    }
  }
}
