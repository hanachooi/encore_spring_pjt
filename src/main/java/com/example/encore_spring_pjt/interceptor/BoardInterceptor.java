package com.example.encore_spring_pjt.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class BoardInterceptor implements HandlerInterceptor {
    // 인터셉터는 프론트에서 링크가 넘어갈 때, 제약을 넣어주는 것.
    // 링크로 /board/ 로 보냈을 때, 인터셉트가 걸리도록 함.

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("debug >>> board interceptor preHandle");
        log.debug("debug >>> board interceptor preHandle");

        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser") == null){
            System.out.println("debug >>> session exits N ");
            response.sendRedirect("/");
        }

        return true;
    }
}
