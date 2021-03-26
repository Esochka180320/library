package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.CustomUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService {

  private final CustomUserRepository customUserRepository;
  private final BookRepository bookRepository;

  public CustomUserService(CustomUserRepository customUserRepository, BookRepository bookRepository) {
    this.customUserRepository = customUserRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional
  public CustomUser addCustomUser(CustomUser customUser) {
    return customUserRepository.save(customUser);
  }

  @Transactional
  public List<CustomUser> findAllCustomUser() {
    return (List<CustomUser>) customUserRepository.findAll();
  }

  @Transactional
  public CustomUser findCustomUser(Integer id) {
    CustomUser customUser = customUserRepository.findCustomUserById(id);
    if (!(customUser == null)) {
      return customUser;
    } else {
      return null;
    }
  }

  @Transactional
  public CustomUser updateCustomUser(CustomUser customUser, List<Integer> id) {
    List<Book> books = new ArrayList<>();
    CustomUser customUser1 = customUserRepository.findCustomUserById(customUser.getId());
    if (!(customUser1 == null)) {
      customUser1.setName(customUser.getName());
      if (!(id ==null)){
        for (Integer integer : id) {
          Book book = bookRepository.findBookById(integer);
          if (!(book == null)) {
            books.add(book);
          }
        }
        customUser1.setBookList(books);
      }
      return customUserRepository.save(customUser1);
    } else {
      return null;
    }
  }

  @Transactional
  public void deleteCustomUser(Integer id) {
    CustomUser customUser = customUserRepository.findCustomUserById(id);
    if (!(customUser == null)) {
      List<Book> books = customUser.getBookList();
      for (Book book : books) {
        book.setFree(true);
      }
      customUserRepository.deleteById(id);
    }
  }

  @Transactional
  public CustomUser takeBook(Integer idBook, Integer idCustomUser) {
    Book book = bookRepository.findBookById(idBook);
    CustomUser customUser = customUserRepository.findCustomUserById(idCustomUser);
    if (!(book == null) && book.getFree() && !(customUser == null)){
      book.setFree(false);
      customUser.addBookList(book);
      return customUser;
    }
    return null;
  }

  @Transactional
  public CustomUser returnBook(Integer idBook, Integer idCustomUser) {
    Book book = bookRepository.findBookById(idBook);
    CustomUser customUser = customUserRepository.findCustomUserById(idCustomUser);
    if (!(book == null) && !book.getFree() && !(customUser == null)){
      book.setFree(true);
      customUser.returnBook(book);
      return customUser;
    }
    return null;
  }
}
