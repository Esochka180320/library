package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ConstructorBinding
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Boolean free = true;
    @ManyToOne
    @JoinColumn(name="custom_user_id")
    CustomUser customUser;

    public Book() {
    }

    public Book(String name, boolean free) {
        this.name =name;
        this.free = free;
    }

    public Book(int id, String name, boolean free) {
        this.id =id;
        this.name =name;
        this.free = free;
    }
}
