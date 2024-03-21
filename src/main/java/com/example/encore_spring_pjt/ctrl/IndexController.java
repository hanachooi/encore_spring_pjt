package com.example.encore_spring_pjt.ctrl;

import ch.qos.logback.core.model.Model;
import com.example.encore_spring_pjt.day0314.UserResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {


    /* // 세션만 이용, 인터럽트 존재하지 않음.
    @RequestMapping("/index.hanwha")
    public String index(HttpSession session){
        System.out.println("debug >> IndexController client path : /");

        // 세션과 관련된 부분
        // 로그인이 되었는지, 안되었는지에 따라, 인터셉션을 실행한다.
        UserResponse response = (UserResponse) session.getAttribute("loginUser");
        System.out.println("debug >>> session Y/N , "+ response);

        return "index";
    }

     */

    /*
    // 세션유무 다른 처리 방식으로
    // 이게 더 보편적임.
    // 세션 유무 다른 처리 방식으로
    @RequestMapping("/index.hanwha")
    // @SessionAttribute(required = false, name = "loginUser") 이거 해주면 세션 저장되서 다시 로그인 안탐
    public String index(@SessionAttribute(required = false, name = "loginUser") UserResponse response, Model model) { // 만약 세션이 있다면 name으로 찾음.
        System.out.println("debug >> IndexController client path : /");
        System.out.println("debug >>> session Y/N , " + response);
        if(response == null){
            return "index";
        } else {
            return "redirect:/board/list.hanwha";
        }
    }

     */

    // 인터럽트가 발생했을 때, 코드
    @RequestMapping("/index.hanwha")
    public String index() {
        System.out.println("debug >> IndexController client path : /");
        return "index";
    }


}
