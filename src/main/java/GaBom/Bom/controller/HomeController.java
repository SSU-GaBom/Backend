package GaBom.Bom.controller;

import GaBom.Bom.model.Board;
import GaBom.Bom.model.User;
import GaBom.Bom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/list")
    public String MakeUser(@RequestBody User user){
        userRepository.save(user);
        return user.getUsername();
    }

    @GetMapping("/list")
    public List<User> UserList(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @DeleteMapping("/list/{user_id}")
    public String one(@PathVariable Long user_id) {
        userRepository.deleteById(user_id);
        return "Deleted id : "+user_id;
    }

//    @DeleteMapping("/list/{user_id}")
//    public String DeleteUser(@){
//        userRepository.f
//        List<User> users = userRepository.findAll();
//        return users;
//    }


}
