package com.example.encore_spring_pjt.ctrl.user;

import com.example.encore_spring_pjt.day0314.UserResponse;
import com.example.encore_spring_pjt.day0314.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService ;
    private final PasswordEncoder passwordEncoder;
    // public String login(String id, String pwd) {
    // public String login(@RequestParam(name = "id") String id,
    //                     @RequestParam(name = "pwd") String pwd) {
    @PostMapping("/login.hanwha")
    public String login(UserRequest params, HttpSession session, RedirectAttributes attr) {

        System.out.println("debug UserController Client path /user/login.hanwha");
        System.out.println("param value >> " + params.getId());
        System.out.println("param value >> " + params.getPwd());
        // 프론트에서 요청한 데이터 id와 pwd 의 값을 UserResponse에 담는다.
        // service를 통해서, 가져옴.
        // 이때, 저장된 회원일 경우에만, response를 갖고 올 수 있기 때문에, 회원인지 아닌지 식별 가능
        UserResponse response = userService.loginService(params); // 데이터를 가져와서 response에 담음
        System.out.println("debug >>> ctrl result , " + response);
        // response가 존재하지 않으면, session.setAttribute를 통해서,
        // setAttribute로 세션 값을 담게 되는데, "loginUser"라는 세션의 이름을 가지고,
        // 여기에, id의 세션값을 담게 됌.
        // 회원이라면, response가 있을 거기에, session 에 값을 담아서, 로그인 상태를 유지시킨다.
        if(response != null){
            // 암호화 이후 로그인 처리 구현부
            // 사용자의 pwd
            String userPwd = params.getPwd();
            // 암호화된 pwd
            String encorePwd = response.getPwd();

            // 비밀번호 일치 여부를 matches() 함수를 이용해서 확인
            if(passwordEncoder.matches(userPwd, encorePwd)){
                System.out.println("debug >>> matches() true");
                response.setPwd("");
                session.setAttribute("loginUser", response);
                return "redirect:/board/list.hanwha";
            }else{
                System.out.println("debug >>> 아이디 일치 했지만 패스워드가 다른 경우");
                attr.addFlashAttribute("failMsg", "비밀번호를 잘못 입력했습니다. " + "입력하신 내용을 다시 확인해주세요.");
            }
            session.setAttribute("loginUser", response);
            return "redirect:/board/list.hanwha";
        } else {
            attr.addFlashAttribute("failMsg", "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력하셨습니다. 입력하신 내용을 다시 확인해주세요.");
            return "redirect:/";
        }

    }

    // 컨트롤러로 로그아웃 하기임. 이걸 인터셉트로 바꿀 수 있음.
    @GetMapping("/logout.hanwha")
    public String logout(HttpSession session){
        System.out.println("UserController.logout.hanwha");
        session.invalidate();
        return "redirect:/";
    }

// 유효성 검사 이용
    @GetMapping("/join.hanwha")
    public String join(@Valid UserRequest params,
                       BindingResult bindingResult, Model model ){
        System.out.println("debug UserController client POST path /user/join.hanwha");


        /*
        실습 구현
        step01 ) 유효성 체크 검증을 통과하면 service registr();
        step02 ) 유효성 체크 검증을 통과화지 못하면 에러메시지를 join.jsp 보내서 출력
        step03 ) UserMapper - insertRow() - UserMapper.xml<insert id = "">
        step04 ) 화면분기는 /
        */

        if(bindingResult.hasErrors()){
            // 로그인을 하지 않고, /user/join.hanwha 에 접근하면, 에러가 발생해야함.
            Map<String, String> result = new HashMap<>();
            List<ObjectError> errors = bindingResult.getAllErrors();

            for(ObjectError e : errors){
                FieldError fieldError = (FieldError) e;

                result.put(fieldError.getField(), e.getDefaultMessage());
            }

            model.addAttribute("result", result);
            return "join";
        }else{
            System.out.println("join success");
            System.out.println(params);

            // 비밀번호 암호화

            System.out.println("debug >>> passwordEncoder , " + passwordEncoder);
            String encoderPwd = passwordEncoder.encode(params.getPwd());
            System.out.println("encoderPwd , " + encoderPwd);
            // 변경된 password는 객체에 다시 바인딩을 해주어야 함.
            params.setPwd(encoderPwd);
            // userService에 param 값을 넘겨준다.
            userService.registerService(params);
            // 초기의 화면으로 redirect 하게 됌.
            return "redirect:/";
        }

    }

    @GetMapping("/kakao_login.hanwha")
    public String kakaoLogin(@RequestParam(value = "code") String code, Model model, HttpSession session){
        System.out.println("debug >>> oauth kakao code , " + code);
        String accessToken = userService.getAccessToken(code);
        Map<String, Object> users = userService.getUserInfo(accessToken);
        System.out.println("debug >>> ctrl result , " + users.get("name"));
        UserResponse response = new UserResponse();
        response.setName((String)(users.get("name")));
        session.setAttribute("loginUser", response);
        session.setAttribute("access_token", accessToken);
        return "redirect:/board/list.hanwha";
    }

    @GetMapping("/kakao_logout.hanwha")
    public String kakaoLogout(HttpSession session){
        System.out.println("debug >>> ctrl kakaoLogout");

        // 1. session으로부터 access token을 얻어와서 카카오서버에 전달 삭제
        String token = (String)session.getAttribute("access_token");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        String result = userService.logout(map);
        System.out.println("debug <<, logout result , " + result);
        session.invalidate();
        return "redirect:/";

    }
}