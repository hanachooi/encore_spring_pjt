package com.example.encore_spring_pjt;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MybatisORMTests {

    @Autowired
    private BoardMapper boardMapper;
    
    @Test
    public void ormSave(){
        System.out.println("debug mapper >> " + boardMapper);

        BoardRequest request = BoardRequest.builder()
                                .title("service")
                                .content("mapper")
                                .writer("encore")
                                .noticeYn(true)
                                .secretYn(true)
                                .build();
        // request.setTitle("orm 수업");
        // request.setContent("mybatis mapping");
        // request.setWriter("encore");
        // request.setNoticeYn(false);
        // request.setSecretYn(false);

        boardMapper.save(request);

        System.out.println("debug >> save success");
    }

    @Test
    public void ormFind() throws Exception {
        System.out.println("debug finder >> ");

        BoardRequest request = BoardRequest.builder()
                                    .idx(1)
                                    .build();

        BoardResponse response = boardMapper.findByIdx(request);
        System.out.println("debug find result >> ");
        
        String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(response);
        System.out.println(response);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(boardJson);

    }

    @Test
    public void ormUpdate() {

        System.out.println("debug update >>");

        BoardRequest request = BoardRequest.builder()
                                .idx(1)
                                .title("happy")
                                .content("not happy")
                                .writer("bak")
                                .build();

        boardMapper.updateByIdx(request);

        System.out.println("debug update result >> ");

    }

    @Test
    public void ormCount(){

        System.out.println("debug count >>");

        int result = boardMapper.count();

        System.out.println("debug count result >> " + result);

    }

    @Test
    public void ormDelete(){

        System.out.println("debug delete >>");

        BoardRequest request = BoardRequest.builder()
                                .idx(2)
                                .build();

        boardMapper.deleteByIdx(request);

    }

    @Test
    public void ormFindAll(){

        System.out.println("debug findAll >>");

        List<BoardResponse> result = boardMapper.findAll();

        for(BoardResponse r : result){
            System.out.println(r);
        }
    }


}
