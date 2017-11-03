package com.spring.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/authenticationFilter")
public class AuthenticationFilter implements Filter{
    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        String uri=httpServletRequest.getRequestURI();

        HttpSession session=httpServletRequest.getSession();
        String id=(String)session.getAttribute("id");

        if(uri.indexOf("loginCheck.do")<0){
            System.out.println("인증되지 않은 요청이 들어왔습니다.");
            if(id==null||id.trim().length()<=0){
                System.out.println("로그인 되지 않은 요청이 들어왔습니다.");
                httpServletResponse.sendRedirect("/member/login.do");
                return;
            }
            else{
                System.out.println("["+id+ " 님의 요청입니다. 이미 로그인 되 있는 상태입니다."+"]");
            }
        }
        else{
            System.out.println("["+id+ " 님의 새로운 요청입니다."+"]");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void destroy() {

    }
}
