package com.example.encore_spring_pjt.ctrl;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.encore_spring_pjt.domain.BoardResponse;

import org.springframework.web.bind.annotation.GetMapping;



// HTTP : code 200, 404, 405(method 불일치), 500(null)
// 특정 도메인 경로를 줄 수 있음, 그때 fastapi에서 question으로 묶었던 것처럼...!
// @RequestMapping("user") : 사용자가 요청하는 경로를 지정 class 위에 묶으면 상위 도메인? 느낌으로다가..
// 메서드 위에 있으면 세부 경로느낌으로다가..
/*- GetMapping
 *- PostMapping
 */


// Controller Interface를 상속하지 않는 그냥 java클래스로 Controller를 만들기..!
@Controller
public class TestController {
    
    // method 옵션을 안주면 get, post 둘다 받게 됨
    /* return type : void, String, ModelAndView -> Full Browsing
     * return type : xxxxDTO, List, Map, Set, ResponseEntity : Rest Api Service
     */
    // @RequestMapping("/")
    // public String index(){

    //     System.out.println(">>>> Test Controller Path / , callback function");

    //     return "index";
    //     // application properties안의 prefix와 suffix를 참고해서 파일을 찾는다.
    //     // viewResolver : application properties를 통해서 파일을 찾을 수 있도록 하는 것
        
    // }

    // @RequestMapping("/")
    // public ModelAndView index(){

    //     System.out.println(">>>> Test Controller Path / , callback function");

    //     ModelAndView mv = new ModelAndView();

    //     mv.setViewName("index");
    //     mv.addObject("msg", "hi~ jsp");
    //     // view와 데이터를 심는 과정

    //     return mv;
    // }

    // model import할 때 spring으로!
    // @RequestMapping("/")
    // public String index(Model model){

    //     System.out.println(">>>> Test Controller Path /test , callback function");

    //     // model에 데이터만 담을 수 있다.
    //     model.addAttribute("msg", "Welcome To SpringBoot with Jsp");

    //     return "index";
    // }

    // @RequestMapping("/test")
    // public void test(){
    //     // 사용자의 return에 맞는 파일이 존재해야한다. (test.jsp)
    // }

    // @GetMapping("/json")
    // @ResponseBody
    // public List<BoardResponse>  json(){
    //     System.out.println(">>>> Test Controller Path /json , callback function");

    //     BoardResponse board = BoardResponse.builder()
    //                             .title("json data")
    //                             .content("json content")
    //                             .writer("encore")
    //                             .build();

    //     List<BoardResponse> list = new ArrayList<>();
    //     list.add(board);      list.add(board);
    //     list.add(board);      list.add(board);

    //     return list;
    // }
    
}
