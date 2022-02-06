package com.example.demo.services.classes;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public List<User> all(){
        return userRepo.findAll();
    }

    public Optional<User> find(Long id){
        return userRepo.findById(id);
    }

    public boolean delete(Long id){
        try {
            userRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public User login(String username, String password){
        return userRepo.getUserByUsernameAndPassword(username, password);
    }
}
