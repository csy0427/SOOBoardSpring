package com.spring.Controller;


import com.spring.Member.dto.MemberVO;
import com.spring.Service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired(required=true)
    private MemberServiceImpl memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String test(){
        return "menu";
    }


    @RequestMapping("signupForm.do")
    public String signupForm(){
        System.out.println("signupForm.do");
        return "/member/signupForm";
    }

    @RequestMapping(value = "signup.do", method = RequestMethod.POST)
    public String signup(@ModelAttribute MemberVO vo, @ModelAttribute("userid") String userid){
        System.out.println("signup.do");
        vo.setUserId(userid);
        memberService.insertMember(vo);
        return "member/login";
    }

    @ResponseBody
    @RequestMapping(value = "confirmId.do", method = RequestMethod.POST)
    public MemberVO confirmId(@ModelAttribute("userid")String userid, HttpServletResponse response) throws Exception {
        System.out.println("confirmId.do");
        MemberVO member = memberService.viewMember(userid);
        return member;
    }

}
