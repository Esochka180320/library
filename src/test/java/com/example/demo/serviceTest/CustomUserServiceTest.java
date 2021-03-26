package com.example.demo.serviceTest;

import com.example.demo.entity.Book;
import com.example.demo.entity.CustomUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomUserRepository;
import com.example.demo.service.BookService;
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
public class CustomUserServiceTest {

    @Autowired
    CustomUserService customUserService;

    @MockBean
    CustomUserRepository customUserRepository;

    @MockBean
    BookRepository bookRepository;

    private final String name = "December";
    private final String name2 = "November";
    private final String name3 = "Book";
    Book book1 = new Book(1, name3, true);
    CustomUser customUser1 = new CustomUser(1, name);
    CustomUser customUser2 = new CustomUser(1, name2);
    List<CustomUser> customUsers = new ArrayList<>();



    @Test
    void addCustomUser_Pass() {
        Mockito.when(customUserRepository.save(customUser1)).thenReturn(customUser1);
        assertEquals(customUserService.addCustomUser(customUser1), customUser1);
    }

    @Test
    void addCustomUser_Fail() {
        Mockito.when(customUserRepository.save(customUser1)).thenReturn(null);
        assertNull(customUserService.addCustomUser(customUser1));
    }

    @Test
    void findCustomUser_Pass() {
        Mockito.when(customUserRepository.findCustomUserById(customUser1.getId())).thenReturn(customUser1);
        assertEquals(customUserService.findCustomUser(customUser1.getId()), customUser1);
    }

    @Test
    void findCustomUser_Fail() {
        Mockito.when(customUserRepository.findCustomUserById(4)).thenReturn(null);
        assertNull(customUserService.findCustomUser(4));
    }

    @Test
    void findAllCustomUser_Pass() {
        List<CustomUser> customUsers = new ArrayList<>();
        customUsers.add(customUser1);
        customUsers.add(customUser2);
        Mockito.when(customUserRepository.findAll()).thenReturn(customUsers);
        assertEquals(customUserService.findAllCustomUser(), customUsers);
    }

    @Test
    void findAllCustomUser_Fail() {
        Mockito.when(customUserRepository.findAll()).thenReturn(null);
        assertNull(customUserService.findAllCustomUser());
    }

    @Test
    void updateCustomUser_Pass() {
        Mockito.when(customUserRepository.findCustomUserById(customUser2.getId())).thenReturn(customUser1);
        Mockito.when(bookRepository.findBookById(null)).thenReturn(book1);
        Mockito.when(customUserRepository.save(customUser1)).thenReturn(customUser2);
        assertEquals(customUserService.updateCustomUser(customUser2, null), customUser2);
    }

    @Test
    void updateCustomUser_Fail() {
        List<Integer> id = new ArrayList<>();
        id.add(book1.getId());
        Mockito.when(customUserRepository.findCustomUserById(4)).thenReturn(null);
        assertNull(customUserService.updateCustomUser(customUser2, id));
    }

    @Test
    void takeBook_Pass() {
        Mockito.when(bookRepository.findBookById(book1.getId())).thenReturn(book1);
        Mockito.when(customUserRepository.findCustomUserById(customUser2.getId())).thenReturn(customUser1);
        assertEquals(customUserService.takeBook(customUser1.getId(), book1.getId()), customUser1);
    }

    @Test
    void takeBook_Fail() {
        Mockito.when(bookRepository.findBookById(4)).thenReturn(null);
        Mockito.when(customUserRepository.findCustomUserById(4)).thenReturn(null);
        assertNull(customUserService.takeBook(4, 5));
    }

    @Test
    void returnBook_Pass() {
        Mockito.when(bookRepository.findBookById(book1.getId())).thenReturn(book1);
        Mockito.when(customUserRepository.findCustomUserById(customUser2.getId())).thenReturn(customUser1);
        assertEquals(customUserService.takeBook(customUser1.getId(), book1.getId()), customUser1);
    }

    @Test
    void returnBook_Fail() {
        Mockito.when(bookRepository.findBookById(4)).thenReturn(null);
        Mockito.when(customUserRepository.findCustomUserById(4)).thenReturn(null);
        assertNull(customUserService.takeBook(4, 5));
    }
}
