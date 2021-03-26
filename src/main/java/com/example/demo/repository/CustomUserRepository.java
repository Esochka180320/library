package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.CustomUser;
import org.springframework.data.repository.CrudRepository;


public interface CustomUserRepository extends CrudRepository<CustomUser, Integer> {

  CustomUser findCustomUserById(Integer id);

  CustomUser findByBookListContains(Book book);
}
