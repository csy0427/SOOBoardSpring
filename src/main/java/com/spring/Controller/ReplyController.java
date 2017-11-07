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

    /*
    @RequestMapping(value = "insert.do")
    public void insert(@ModelAttribute ReplyVO vo, HttpSession session){
        String userid=(String)session.getAttribute("userid");
        vo.setUserId(userid);
        replyService.create(vo);
    }*/

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

    /*
    @RequestMapping(value = "/insert/{boardnumber}", method=RequestMethod.POST)
    public ResponseEntity<String> inserts(@RequestBody ReplyVO vo, @PathVariable("boardnumber") String boardnumber,HttpSession session){
        ResponseEntity<String> entity=null;
        String userid=(String)session.getAttribute("userid");
        String boardUserId=(String)session.getAttribute("boardUserId");
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

    @RequestMapping(value = "list.do")
    public void list(@RequestParam("boardnumber") String boardnumber,@RequestParam("curPage") String curPage, HttpSession session){
        String userid=(String)session.getAttribute("userid");
        replyService.list(boardnumber);
    }
*/
    // 2_1. 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam String boardnumber,	@RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session){
        // 페이징 처리
        int count = replyService.count(boardnumber); // 댓글 갯수
        ReplyPager replyPager = new ReplyPager(count, curPage);
        List<ReplyVO> list = replyService.listAll(replyPager,boardnumber,session);
        // 뷰이름 지정
        mav.setViewName("board/replyList");
        // 뷰에 전달할 데이터 지정
        mav.addObject("list", list);
        mav.addObject("replyPager", replyPager);
        // replyList.jsp로 포워딩
        return mav;
    }

    @RequestMapping(value="/list/{boardnumber}/{curPage}", method= RequestMethod.GET)
    public ModelAndView replyList(@PathVariable("boardnumber") String boardnumber, @PathVariable int curPage, ModelAndView mav, HttpSession session){
        // 페이징 처리
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        int count = replyService.count(boardnumber); // 댓글 갯수
        ReplyPager replyPager = new ReplyPager(count, curPage);
        List<ReplyVO> list = replyService.listAll(replyPager,boardnumber,session);
        System.out.println(replyPager.toString());
        mav.setViewName("board/replyList");
        session.setAttribute("list",list);
        session.setAttribute("replyPager",replyPager);
        return mav;
    }

    @RequestMapping(value="/detail/{rno}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rno") String rno, ModelAndView mav,HttpSession httpSession){
        ReplyVO vo = replyService.detail(rno);
        mav.setViewName("board/replyDetail");
        httpSession.setAttribute("vo", vo);
        return mav;
    }
}
