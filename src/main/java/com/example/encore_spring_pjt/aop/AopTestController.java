package com.example.encore_spring_pjt.aop;


import com.example.encore_spring_pjt.aop.domain.DataRequestDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// 사용되는 모듈을 각자 서버를 기동해야 함. 그러면, /aop 는 각각의 서비스 중 하나이고,
// 이는 이용되는 서비스의 서버 이름이 되게 된다.
@RestController
@RequestMapping("/aop")
public class AopTestController {

    @GetMapping("/test01")
    public ResponseEntity<Void> test01(){
        //System.out.println("비즈니스로직 수행 전 로깅");     // 이런 건 aop로 빠져야 함.

        System.out.println("AopTestController.test01");

        //System.out.println("비즈니스로직 수행 후 로깅");

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/test02/{msg}")
    public ResponseEntity<Void> tes02(@PathVariable("msg") String msg ){
        //System.out.println("비즈니스로직 수행 전 로깅");

        System.out.println("AopTestController.test02 msg : " + msg );

        //System.out.println("비즈니스로직 수행 후 로깅");

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/test03")
    public ResponseEntity<DataRequestDTO> test03(@RequestBody DataRequestDTO param){
        System.out.println("AopTestController.getMethodName");
        return new ResponseEntity<DataRequestDTO>(param, HttpStatus.OK);
    }

    @PostMapping("/test04")
    public ResponseEntity<DataRequestDTO> test04(@RequestBody DataRequestDTO param) throws Exception{
        System.out.println("AopTestController.test04");
        throw new Exception("예외발생");
    }
}
