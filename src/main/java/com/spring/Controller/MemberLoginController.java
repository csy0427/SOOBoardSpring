package com.spring.Controller;

import com.spring.Member.dto.MemberVO;
import com.spring.Service.MemberLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class MemberLoginController {

    @Autowired(required=true)
    private MemberLoginServiceImpl memberLoginService;

    @RequestMapping("login.do")
    public String login(){
        return "/member/login";
    }

    @RequestMapping("loginCheck.do")
    public String loginCheck(@ModelAttribute("userid")String userid, @ModelAttribute MemberVO vo, HttpSession session){
        System.out.println("loginCheck.do string");
        vo.setUserId(userid);
        session.setAttribute("id",vo.getUserId());
        boolean result=memberLoginService.loginCheck(session, vo);
        ModelAndView modelAndView=new ModelAndView();
        String returnView="";
        if(result==true){
            System.out.println("loginCheck.do true string");
            modelAndView.addObject("msg","success");
            session.setAttribute("id",vo.getUserId());
            returnView="board/listForm";
        }
        else{
            System.out.println("loginCheck.do false string");
            modelAndView.addObject("msg","failure");
            returnView="member/login";
        }
        return returnView;
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        memberLoginService.logout(session);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg","logout");
        return "login";
    }

}
