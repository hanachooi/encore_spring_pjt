package com.example.encore_spring_pjt.ctrl.board;

import java.util.List;
import java.util.Optional;

import com.example.encore_spring_pjt.paging.PageDTO;
import com.example.encore_spring_pjt.paging.PageResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardServiceImpl;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/board")
public class BoardMyBatisController {

    @Resource(name = "board")
    BoardServiceImpl service;


    @RequestMapping("/list.hanwha")
    public String list(PageDTO params ,Model model) {

        System.out.println("debug BoardController Client path /board/list.hanwha");

        // 페이징 추가
        PageResponse<BoardResponse> result = service.listBoard(params);

        model.addAttribute("result", result);

        return "listPage";
    }

    // @GetMapping("/view.hanwha/{idx}")
    /*
    @GetMapping("/view.hanwha")
    public String view(BoardRequest params, Model model){
    // public String view(@PathVariable("idx") Integer idx){
        System.out.println("debug BoardController Client path /board/view.hanwha");
        System.out.println(params.getIdx());
         /*
         * view로부터 키(idx)값을 전달받고 객체로 바인딩되서 service에 전달
         * response객체를 반환받고
         * 해당 response 객체 Model에 심어서 View 페이지로 전달
         */
    /*
        Optional<BoardResponse> result = service.findBoard(params);     
        model.addAttribute("result", result);

        return "view";
    }
    */

    // 조회수 중복 방지를 위해, 커스텀.
    // 이때는 세션이 아닌, 쿠키를 이용해야 한다.
    // 쿠키는 웹(Client)에서 제공한다.  : request.getCookies()
    // 세션은 서버 상에서 제공한다. : request.getSession()
    // setMaxAge(60 * 60 * 24)  // 세션이나 쿠키가 유지가 되는 시간을 설정할 수 있음. ( 초, 분, 시) (60*60*24) 는 하루를 의미함.
    @GetMapping("/view.hanwha")
    public String view(BoardRequest params,
                       Model model,
                       HttpServletRequest request,
                       HttpServletResponse response){
        System.out.println("debug BoardController Client path /board/view.hanwha");
        System.out.println(params.getIdx());

        // 조회수 ++;
        //Optional<BoardResponse> responseEntity = service.findBoard(params);
        //model.addAttribute("result", responseEntity);

        // 수정사항 발생
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            // 쿠키 정보가 있을 때
            // service.findBoardNotCnt(params);
        }else{
            // 쿠키 정보가 없을 때
            // 쿠키는 객체를 심을 수 없다.
            Cookie cookie = new Cookie("view_upcnt", params.getIdx().toString());
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            service.findBoard(params);
            Optional<BoardResponse> responseEntity = service.findBoard(params);
            // Optional 이면, 값이 있을 수도 있고, 없을 수도 있기 때문에, .get()을 통해서, 가져온다.
            model.addAttribute("result", responseEntity.get());
        }

        return "view";
    }

    @GetMapping("/write.hanwha")
    public String writeForm(BoardRequest params, Model model) {

        System.out.println("debug BoardController Client path /board/write.hanwha");
        System.out.println(params);

        if(params.getIdx() != null){
            System.out.println("debug >> update");
            Optional<BoardResponse> response = service.findBoard(params);
            // Optional 로 바꾸고 나서, 값을 받아와야 하면, .get() 과 같이, 함수를 사용해야 함.
            model.addAttribute("response", response.get());
        }

        return "write";
    }

    @PostMapping("/write.hanwha")
    public String save(BoardRequest params) {
        
        System.out.println("debug BoardController Client path /board/save.hanwha");
        System.out.println(params);

        if(params.getNoticeYn() == null){params.setNoticeYn(false);}
        params.setSecretYn(false);
        // 이렇게 하거나 builder 주석처리 하거나 

        service.saveBoard(params);
        
        return "redirect:/board/list.hanwha";
    }

    @GetMapping("/delete.hanwha")
    public String delete(BoardRequest params) {
        System.out.println("debug BoardController Client path /board/delete.hanwha");
        
        service.deleteBoard(params);

        return "redirect:/board/list.hanwha";
    }

    @PostMapping("/update.hanwha")
    public String update(BoardRequest params) {
        System.out.println("debug BoardController Client path /board/update.hanwha");
        
        System.out.println(params);
        service.updateBoard(params);
        
        return "redirect:/board/list.hanwha";
    }

}
