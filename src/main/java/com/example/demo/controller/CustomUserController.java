package com.example.demo.controller;


import com.example.demo.entity.CustomUser;
import com.example.demo.service.CustomUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomUserController {
    private final CustomUserService customUserService;

    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @PostMapping  ("/addCustomUser")
    public ResponseEntity<?> addCustomUser(@RequestBody CustomUser data) {
            return ResponseEntity.ok(customUserService.addCustomUser(data));
    }

    @GetMapping  ("/findAllCustomUser")
    public ResponseEntity<?> findAllCustomUser() {
        return ResponseEntity.ok(customUserService.findAllCustomUser());
    }

    @GetMapping  ("/findCustomUser")
    public ResponseEntity<?> findCustomUser(@RequestParam Integer id) {
        return ResponseEntity.ok(customUserService.findCustomUser(id));
    }

    @PutMapping  ("/updateCustomUser")
    public ResponseEntity<?> updateCustomUser(@RequestBody CustomUser data, @RequestParam List<Integer> id) {
        return ResponseEntity.ok(customUserService.updateCustomUser(data, id));
    }

    @DeleteMapping  ("/deleteCustomUser")
    public ResponseEntity<?> deleteCustomUser(@RequestParam Integer id) {
        customUserService.deleteCustomUser(id);
        return ResponseEntity.ok("delete");
    }

    @PutMapping  ("/takeBook")
    public ResponseEntity<?> takeBook(@RequestParam Integer idBook , @RequestParam Integer idCustomUser) {
        return ResponseEntity.ok(customUserService.takeBook(idBook , idCustomUser));
    }

    @PutMapping  ("/returnBook")
    public ResponseEntity<?> returnBook(@RequestParam Integer idBook , @RequestParam Integer idCustomUser) {
        return ResponseEntity.ok(customUserService.returnBook(idBook , idCustomUser));
    }
}
