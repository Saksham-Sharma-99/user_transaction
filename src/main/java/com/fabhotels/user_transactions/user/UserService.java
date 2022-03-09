package com.fabhotels.user_transactions.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public Status registerUser(User newUser){
        List<User> users = userRepository.findAll();

        for(User user: users){
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    public Status loginUser(User user){
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    public Status logOut(User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }

    public Status updateUser(User user){
        Optional<User> currUser = userRepository.findById(user.getId());

        currUser.ifPresent(currentUser->{
            currentUser.setName(user.getName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword((user.getPassword()));
            userRepository.save(currentUser);
        });

        if (currUser.isPresent()) return Status.SUCCESS;
        else return Status.FAILURE;
    }

}
