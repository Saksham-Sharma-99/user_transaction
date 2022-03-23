package com.fabhotels.user_transactions.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public Status registerUser(String name, String email, String password){
//        List<User> users = userRepository.findAll();
//
//        for(User user: users){
//            if (user.equals(newUser)) {
//                System.out.println("User Already exists!");
//                return Status.USER_ALREADY_EXISTS;
//            }
//        }
//
//        userRepository.save(newUser);

        User user = userRepository.findByEmail(email);

        if(user!=null){
            System.out.println("User Already exists!");
            return Status.USER_ALREADY_EXISTS;
        }

        User newUser = new User(name,email,password);

        userRepository.save(newUser);

        return Status.SUCCESS;
    }

    public Status loginUser(String email,String password){
//        List<User> users = userRepository.findAll();
//        for (User other : users) {
//            if (other.equals(user)) {
//                user.setLoggedIn(true);
//                userRepository.save(user);
//                return Status.SUCCESS;
//            }
//        }

        User user = userRepository.findByEmail(email);
         if(password == user.getPassword()){
             user.setLoggedIn(true);
             userRepository.save(user);
             return Status.SUCCESS;
         }

        return Status.FAILURE;
    }

    public Status logOut(Integer userId) {
        Optional<User> currUser = userRepository.findById(userId);

        currUser.ifPresent(currentUser->{
            currentUser.setLoggedIn(false);
            userRepository.save(currentUser);
        });

        if (currUser.isPresent()) return Status.SUCCESS;
        else return Status.FAILURE;
//        List<User> users = userRepository.findAll();
//        for (User other : users) {
//            if (other.equals(user)) {
//                user.setLoggedIn(false);
//                userRepository.save(user);
//                return Status.SUCCESS;
//            }
//        }
//        return Status.FAILURE;
    }

    public HashMap<String,String> getUser(Integer id){
        HashMap<String,String> response = new HashMap<>();
        Optional<User> user =  userRepository.findById(id);
        user.ifPresent(u->{
            response.put("name",u.getName());
            response.put("email",u.getEmail());
        });
        return response;
    }

    public Status updateUser(Integer userId,HashMap<String,String> data){
        Optional<User> currUser = userRepository.findById(userId);

        currUser.ifPresent(currentUser->{
            currentUser.setName(data.containsKey("name") ? data.get("name") : currentUser.getName());
            currentUser.setEmail(data.containsKey("email") ? data.get("email") : currentUser.getEmail());
            currentUser.setPassword((data.containsKey("password")? data.get("password"):currentUser.getPassword()));
            userRepository.save(currentUser);
        });

        if (currUser.isPresent()) return Status.SUCCESS;
        else return Status.FAILURE;
    }

}
