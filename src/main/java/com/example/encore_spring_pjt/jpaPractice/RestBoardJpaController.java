package com.example.encore_spring_pjt.jpaPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post_jpa")
public class RestBoardJpaController {

    @Autowired
    private PostServiceImpl service;

    // 전체 조회 업무
    // GET : http://ip:port/board/jpa/list : postman test url
    // Controller 에서는, ResponseEntity 로 받아서, JSON 파일 형식으로 넘겨줄 수 있게 만든다.
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BoardEntity>> findAllBoard(){
        System.out.println("RestBoardJpaController.list");
       List<BoardEntity> list = service.findAllBoard(); // 이 BoardEntity 리스트형태를 ResponseEntity 의 형태로 바꾸어 주어야 함.

        return ResponseEntity.ok().body(list);
    }
/*
    @GetMapping(value = "/list/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BoardEntity>> findByName(){
        System.out.println("RestBoardJpaController.findByName");
        List<BoardEntity> list = service.findByName();


    }
*/
}
