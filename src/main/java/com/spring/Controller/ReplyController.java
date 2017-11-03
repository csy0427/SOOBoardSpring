package com.spring.Controller;


import com.spring.Reply.dto.ReplyVO;
import com.spring.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {

    @Autowired
    ReplyService replySerivce;

    @RequestMapping(value = "insert.do")
    public void insert(@ModelAttribute ReplyVO vo, HttpSession session){
        String userid=(String)session.getAttribute("userid");
        vo.setUserId(userid);
        replySerivce.create(vo);
    }


}
