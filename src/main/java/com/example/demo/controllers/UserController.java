package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.services.classes.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> getAllUsers(){
        return userService.all();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto user){
        User mySer = userService.login(user.getUsername(), user.getPassword());
        if(mySer!=null){
            return ResponseEntity.ok(mySer);
        }
        return ResponseEntity.badRequest().body("Error");
    }

    @GetMapping("/find")
    public ResponseEntity find(@RequestParam Long id){
        Optional<User> user = userService.find(id);
        if(user.get()!=null){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.badRequest().body("Error");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        boolean isRemoved = userService.delete(id);

        if (isRemoved) {
            return ResponseEntity.ok("Done");
        }

        return ResponseEntity.badRequest().body("Not found");
    }
}
