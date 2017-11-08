package com.spring.Controller;


import com.spring.Reply.dto.ReplyPager;
import com.spring.Reply.dto.ReplyVO;
import com.spring.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @RequestMapping(value = "insert.do", method=RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody ReplyVO vo,HttpSession session){
        ResponseEntity<String> entity=null;
        String userid=(String)session.getAttribute("userid");
        String boardUserId=(String)session.getAttribute("boardUserId");
        String boardnumber= (String) session.getAttribute("boardnumber");
        try {
            vo.setReplyer(userid);
            vo.setUserId(boardUserId);
            vo.setBoardnumber(boardnumber);
            System.out.println(vo.toString());
            replyService.create(vo);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            entity=new ResponseEntity<String>(String.valueOf(e.getStackTrace()), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam String boardnumber,	@RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session){
        int count = replyService.count(boardnumber); // 댓글 갯수
        ReplyPager replyPager = new ReplyPager(count, curPage);
        List<ReplyVO> list = replyService.listAll(replyPager,boardnumber,session);
        mav.setViewName("board/replyList");
        mav.addObject("list", list);
        mav.addObject("replyPager", replyPager);
        session.setAttribute("boardnumber",boardnumber);
        return mav;
    }

    @RequestMapping(value="/list/{boardnumber}/{curPage}", method= RequestMethod.GET)
    public ModelAndView replyList(@PathVariable("boardnumber") String boardnumber, @PathVariable int curPage, ModelAndView mav, HttpSession session){
        int count = replyService.count(boardnumber); // 댓글 갯수
        ReplyPager replyPager = new ReplyPager(count, curPage);
        List<ReplyVO> list = replyService.listAll(replyPager,boardnumber,session);
        System.out.println(list.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        mav.setViewName("board/replyList");
        session.setAttribute("list",list);
        session.setAttribute("replyPager",replyPager);
        session.setAttribute("boardnumber",boardnumber);
        return mav;
    }

    @RequestMapping(value="/detail/{rno}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rno") String rno, ModelAndView mav,HttpSession httpSession){
        System.out.println("replyDetail Controller");
        ReplyVO vo = replyService.detail(rno);
        mav.setViewName("board/replyDetail");
        httpSession.setAttribute("post", vo);
        return mav;
    }

    @RequestMapping(value = "/update/{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> replyUpdate(@PathVariable("rno") String rno, @RequestBody ReplyVO  vo){
        ResponseEntity<String> entity =null;
        try {
            vo.setRno(rno);
            replyService.update(vo);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            // 댓글 수정이 실패하면 실패 상태메시지 저장
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/delete/{rno}")
    public ResponseEntity<String> replyDelete(@PathVariable("rno") String rno){
        ResponseEntity<String> entity=null;
        try{
            replyService.delete(rno);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            // 댓글 삭제가 실패하면 실패 상태메시지 저장
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
