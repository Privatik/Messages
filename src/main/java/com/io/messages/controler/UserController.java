package com.io.messages.controler;


import com.io.messages.domain.User;
import com.io.messages.exception.NotFoundException;
import com.io.messages.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements IFound<User> {
    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
       // this.userAndChatService = userAndChatService;
    }

    @GetMapping("/user")
    public List<User> getListUser()
    {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable Long id) {
        return foundElement(id);
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user)
    {
        //user.setChatList(new HashSet<>());
        return userRepo.save(user);
    }

    @PutMapping("/user/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user)
    {
        User userFromOb = foundElement(id);
        BeanUtils.copyProperties(user, userFromOb, "id");
        return userRepo.save(userFromOb);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUSer(@PathVariable Long id)
    {
        userRepo.deleteById(id);
    }

    @DeleteMapping("/user/del")
    public void deleteAllUser()
    {
        userRepo.deleteAll();
    }

    @Override
    public User foundElement(Long id) {
        return userRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
