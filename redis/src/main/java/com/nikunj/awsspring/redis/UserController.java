package com.nikunj.awsspring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/redis/")
public class UserController {

    @Autowired
    UserRepoImpl userRepoImpl;

    @GetMapping("user")
    public User getUser(String id){
        return userRepoImpl.findUser(id);
    }

    @PostMapping("user/add")
    public void saveUser(@RequestBody User user){
        userRepoImpl.save(user);
    }

    @GetMapping("users")
    public Map<String, User> getUser(){
        return userRepoImpl.findAllUsers();
    }

    @PutMapping("user/update")
    public void updateUser(User user){
        userRepoImpl.save(user);
    }

    @DeleteMapping("delete/user")
    public void deleteUser(String id){
        userRepoImpl.delete(id);
    }

}
