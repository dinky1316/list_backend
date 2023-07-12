package com.hang.backend.controller;

import com.hang.backend.exception.UserNotFoundException;
import com.hang.backend.model.User;
import com.hang.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")   // * : 모든 곳에서 이 요청을 문제 없이 사용 가능. 모든요청이 허용된다 / 주소: 거기서 오는것만 허용
@RequiredArgsConstructor // 생성자를 롬북이 자동으로 만들어줌
public class UserController {


    private final UserRepository userRepository;

    // @RequiredArgsConstructor로 생성자를 롬북이 자동으로 만들어줌
//    @Autowired
//    public UserController(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
    
    // user 주소로 데이터를 보내면 저장해줌ㅌ
    @PostMapping("/user")
    User newUSer(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> newUser(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUSerId(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }
}
