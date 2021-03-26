package com.example.demo.controllerTest;

import com.example.demo.controller.CustomUserController;
import com.example.demo.entity.Book;
import com.example.demo.entity.CustomUser;
import com.example.demo.service.CustomUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class CustomUserControllerTest {
  @Autowired
  CustomUserController customUserController;

  @MockBean
  CustomUserService customUserService;

  private final String name = "December";
  private final String name2 = "November";
  private final String name3 = "Book";
  Book book1 = new Book(1, name3, true);
  CustomUser customUser1 = new CustomUser(1, name);
  CustomUser customUser2 = new CustomUser(1, name2);
  List<CustomUser> customUsers = new ArrayList<>();



  @Test
  void addCustomUser_Pass() {
    Mockito.doReturn(customUser1)
            .when(customUserService)
            .addCustomUser(customUser1);
    assertEquals(customUserController.addCustomUser(customUser1).getBody(), customUser1);
  }

  @Test
  void addCustomUser_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .addCustomUser(customUser1);
    assertNull(customUserController.addCustomUser(customUser1).getBody());
  }

  @Test
  void findCustomUser_Pass() {
    Mockito.doReturn(customUser1)
            .when(customUserService)
            .findCustomUser(1);
    assertEquals(customUserController.findCustomUser(1).getBody(), customUser1);
  }

  @Test
  void findCustomUser_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .findCustomUser(1);
    assertNull(customUserController.findCustomUser(1).getBody());
  }

  @Test
  void findAllCustomUser_Pass() {
    customUsers.add(customUser1);
    customUsers.add(customUser2);
    Mockito.doReturn(customUsers)
            .when(customUserService)
            .findAllCustomUser();
    assertEquals(customUserController.findAllCustomUser().getBody(), customUsers);
  }

  @Test
  void findAllCustomUser_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .findAllCustomUser();
    assertNull(customUserController.findAllCustomUser().getBody());
  }

  @Test
  void updateCustomUser_Pass() {
    Mockito.doReturn(customUser2)
            .when(customUserService)
            .updateCustomUser(customUser2, null);
    assertEquals(customUserController.updateCustomUser(customUser2, null).getBody(), customUser2);
  }

  @Test
  void updateCustomUser_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .updateCustomUser(customUser2, null);
    assertNull(customUserController.updateCustomUser(customUser2, null).getBody());
  }

  @Test
  void deleteCustomUser() {
    assertEquals(customUserController.deleteCustomUser(1).getBody(), "delete");
  }

  @Test
  void takeBook_Pass() {
    List<Book> books = new ArrayList<>();
    books.add(book1);
    CustomUser customUser3 = new CustomUser(3, name , books);
    Mockito.doReturn(customUser3)
            .when(customUserService)
            .takeBook(1, 1);
    assertEquals(customUserController.takeBook(1, 1).getBody(), customUser3);
  }

  @Test
  void takeBook_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .takeBook(1, 1);
    assertNull(customUserController.takeBook(1, 1).getBody());
  }

  @Test
  void returnBook_Pass() {
    Mockito.doReturn(customUser1)
            .when(customUserService)
            .returnBook(3, 1);
    assertEquals(customUserController.returnBook(3, 1).getBody(), customUser1);
  }

  @Test
  void returnBook_Fail() {
    Mockito.doReturn(null)
            .when(customUserService)
            .returnBook(1, 1);
    assertNull(customUserController.returnBook(1, 1).getBody());
  }
}
