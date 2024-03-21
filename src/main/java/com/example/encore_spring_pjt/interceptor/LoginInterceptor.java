package com.example.encore_spring_pjt.interceptor;
// filter는 웹에서 제공하고, Interceptor는 프로그램에서 제공함.

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
인터셉터의 작동원리 및 순서
       XXXXInterceptor(preHandler, postHandler)
client ----------------> preHandler ( preHandler에서 요청을 가로채면 Controller가 수행되지 않음. )
client ----------------> postHandler ( Controller --> postHandler --> View(redirect) 즉, postHandler가 다른 View로 보낼 수 있음 )
*/
public class LoginInterceptor implements HandlerInterceptor {

    // prehandler이 수행되면, Controller 가 수행되지 않고,
    // PostHandler가 수행되면, Controller 가 수행된다.

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("debug >>>> LoginInterceptor preHandler >>>>>");
        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser") != null ){
            System.out.println("LoginInterceptor.preHandle");
            response.sendRedirect("/board/list.hanwha");
        }
        return true;
    }

    /*
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    */
}
