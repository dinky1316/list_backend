package com.hang.backend.controller;

import com.hang.backend.exception.ListNotFoundException;
import com.hang.backend.model.ToDoList;
import com.hang.backend.repository.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// * : 모든 곳에서 이 요청을 문제 없이 사용 가능. 모든요청이 허용된다 / 주소: 거기서 오는것만 허용
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor // 생성자를 롬북이 자동으로 만들어줌
public class ListController {


    private final ListRepository listRepository;

    // list 추가
    @PostMapping("/addList")
    ToDoList newUSer(@RequestBody ToDoList newList) {
        return listRepository.save(newList);
    }

    // 전체 list 보기
    @GetMapping("/list")
    java.util.List<ToDoList> newList() {
        return listRepository.findAll();
    }
    
    // ID로 list 찾기
    @GetMapping("/list/{id}")
    ToDoList getListId(@PathVariable Long id) {
        return listRepository.findById(id).orElseThrow(() -> new ListNotFoundException(id));
    }

    @GetMapping("/searchList")
    List<ToDoList> getListId(@RequestParam("listName") String listName) {
        System.out.println(listName);
        return listRepository.findByListNameLike(listName);
    }
    
    // list 변경
    @PutMapping("/list/{id}")
    ToDoList updateList(@RequestBody ToDoList newList, @PathVariable Long id) {
        return listRepository.findById(id)
                .map(list -> {
                    list.setListName(newList.getListName());
                    list.setContent(newList.getContent());
                    list.setEmoji(newList.getEmoji());
                    return listRepository.save(list);
                }).orElseThrow(() -> new ListNotFoundException(id));

    }
    
    // list 삭제
    @DeleteMapping("/list/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!listRepository.existsById(id)) {
            throw new ListNotFoundException(id); // 찾는 유저가 없을경우 예외처리
        }
        listRepository.deleteById(id);
        return "리스트 아이디 : " + id + "는 삭제되었습니다.";
    }
}
