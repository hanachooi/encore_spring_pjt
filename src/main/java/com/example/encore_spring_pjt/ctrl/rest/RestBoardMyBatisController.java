package com.example.encore_spring_pjt.ctrl.rest;

import com.example.encore_spring_pjt.paging.PageDTO;
import com.example.encore_spring_pjt.paging.PageResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/*
 * HTTP 통신 : client가 웹 서버(was)에게 요청의 목적을 알리는 수단
 * GET - Query String (?key=value (파라메타), /{value} ) / POST / PUT / DELETE
 * 
 * RestController 통신을 위한 템플릿이 제공 
 * - RequestEntity, ResponseEntity
 */

@RestController
@RequestMapping("/board_rest")
public class RestBoardMyBatisController {


    // MyBatis 이용
    @Resource(name = "board")
    private BoardService service;

    // 전체 조회
    @RequestMapping("/list.hanwha")
    public String list(@ModelAttribute("params") PageDTO params , Model model) {

        System.out.println("debug BoardController Client path /board/list.hanwha");

        // 페이징 추가
        PageResponse<BoardResponse> result = service.listBoard(params);

        model.addAttribute("result", result);

        return "list";
    }

    // 삭제
    @DeleteMapping(value = "/delete/{idx}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("idx") Integer idx){
        System.out.println("debug RestBoardController client path /board_rest/delete/idx");

        Integer result = service.deleteBoard(BoardRequest.builder().idx(idx).build());
        System.out.println(result + "번 게시글 삭제 완료");

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 게시글 번호로 조회
    @GetMapping(value = "/view/{idx}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<BoardResponse>> getMethodName(@PathVariable("idx") Integer idx) {
        System.out.println("debug RestBoardController client path /board_rest/view/idx");

        Optional<BoardResponse> result = service.findBoard(BoardRequest.builder().idx(idx).build());

        System.out.println("debug >> isPresent : " + result.isPresent());
        System.out.println("debug >>> view result : " + result);

        return new ResponseEntity<Optional<BoardResponse>>(result, HttpStatus.OK);
    }
    
    // 게시물 번호로 수정
    // @PutMapping(value = "/update/{idx}/{title}/{content}/{writer}", produces = {MediaType.APPLICATION_JSON_VALUE})
    // public ResponseEntity<Void> update(@PathVariable("idx") Integer idx,
    //                                                     @PathVariable("title") String title,
    //                                                     @PathVariable("content") String content,
    //                                                     @PathVariable("writer") String writer) {
        
    //     System.out.println("debug RestBoardController client path /board_rest/update/idx ...");


    //     Integer result = service.updateBoard(BoardRequest.builder()
    //                                                     .idx(idx)
    //                                                     .title(title)
    //                                                     .content(content)
    //                                                     .writer(writer)
    //                                                     .build());
    //     System.out.println(result + "번 게시글 수정 완료");

    //     return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    // }

    // 객체 형식으로 테스트 진행
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(BoardRequest params) {
        
        System.out.println("debug RestBoardController client path /board_rest/update");
        System.out.println("debug >> params : " + params);

        Integer result = service.updateBoard(params);
        System.out.println(result + "번 게시글 수정 완료");

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 입력
    @PostMapping( value = "/write", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String,String>> save(BoardRequest params) {
        
        System.out.println("debug RestBoardController client path /board_rest/write");
        System.out.println("debug >> params : " + params);

        Integer result = service.saveBoard(params);

        Map<String,String> responseMap = new HashMap<String,String>();
        responseMap.put(String.valueOf(result), result + "번에 새 글이 추가되었습니다.");
        
        return new ResponseEntity<Map<String,String>>(responseMap, HttpStatus.OK);
    }


    
}