package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ConstructorBinding
public class CustomUser {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  @OneToMany
  List<Book> bookList = new ArrayList<>();

  public CustomUser() {
  }

  public CustomUser(int id, String name) {
    this.id=id;
    this.name=name;
  }

  public CustomUser(int id, String name, List<Book> books) {
    this.id=id;
    this.name=name;
    this.bookList = books;
  }

  public void addBookList(Book book) {
    this.bookList.add(book);
  }

  public void returnBook(Book book) {
    this.bookList.remove(book);
  }
}
