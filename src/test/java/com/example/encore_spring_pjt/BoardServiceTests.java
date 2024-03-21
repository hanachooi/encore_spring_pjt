package com.example.encore_spring_pjt;

import java.util.List;

import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;

@SpringBootTest
public class BoardServiceTests {
    
    // @Autowired
    // private BoardService boardServiceImpl;

    @Resource(name = "board")
    private BoardService service;

    @Test
    public void serviceSaveTest(){
        System.out.println("debug service >> " + service);

        // Builder를 사용하지 않는 레거시(legacy)형식
        // BoardRequest request = new BoardRequest();
        // request.setTitle("service 수업");
        // request.setContent("service를 이용한 DAO(Mapper) 연결");
        // request.setWriter("encore");
        // request.setNoticeYn(false);
        // request.setSecretYn(false);

        // chaning 기법 : 생성자를 순서가 아니라 지정해서 할당할 수 있음 
        BoardRequest request = BoardRequest.builder()
                                    .title("service")
                                    .content("mapper")
                                    .writer("encore")
                                    .noticeYn(true)
                                    .secretYn(true)
                                    .build();

        Integer idx = service.saveBoard(request);
        System.out.println("입력한 데이터의 키 값을 출력 : " + idx);
    }

    @Test
    public void serviceFindTest(){

        BoardRequest request = BoardRequest.builder()
                                    .idx(1)
                                    .build();

        BoardResponse response = service.findBoard(request);

        System.out.println("debug find result >> ");
        System.out.println(response);
    }

    @Test
    public void serviceUpdateTest(){
        BoardRequest request = BoardRequest.builder()
                                .idx(1)
                                .title("happy")
                                .content("not happy")
                                .writer("bak")
                                .build();

        Integer result =  service.updateBoard(request);

        System.out.println("debug update result >> " + result + "행이 수정되었습니다.");

    }

    @Test
    public void serviceDeleteTest(){

        System.out.println("debug delete >>");

        BoardRequest request = BoardRequest.builder()
                                .idx(2)
                                .build();

        Integer result = service.deleteBoard(request);

        System.out.println("debug update result >> " + result + "행이 삭제되었습니다.");

    }

    /*
    @Test
    public void serviceCntTest(){
        System.out.println("debug count >>");

        int result = service.cntBoard();

        System.out.println("debug count result >> " + result);
    }
*/
    /*
    @Test
    public void serviceFindAllTest(){

        System.out.println("debug findAll >>");

        List<BoardResponse> result = service.listBoard(request);

        for(BoardResponse r : result){
            System.out.println(r);
        }
    }
     */

}
