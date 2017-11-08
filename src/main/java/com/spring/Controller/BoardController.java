package com.spring.Controller;


import com.spring.Board.dto.BoardPager;
import com.spring.Board.dto.BoardVO;
import com.spring.Member.dto.MemberVO;
import com.spring.Service.BoardService;
import com.spring.Service.ReplyService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HttpRequest.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value="/")
    public String test(){
        return "index";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption,
                             @RequestParam(defaultValue="") String keyword,
                             @RequestParam(defaultValue="1") int curPage,HttpSession httpSession) throws Exception{

        int count = boardService.countArticle(searchOption, keyword);

        BoardPager boardPager = new BoardPager(count, curPage);
        Map<String, Object> tmpMap = boardService.listAll(boardPager, searchOption, keyword);
        List<BoardVO> list=new ArrayList<BoardVO>();
        Map<String, Object> map = new HashMap<String, Object>();
        for(String key : tmpMap.keySet()){
            list.add((BoardVO) tmpMap.get(key));
        }

        map.put("list", list);
        map.put("count", count);
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        map.put("boardPager", boardPager);
        System.out.println("list controller called**********");
        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map);
        httpSession.setAttribute("map",map);
        mav.setViewName("board/list"); // 뷰를 list.jsp로 설정

        return mav;
    }

    @RequestMapping(value = "read.do",method = RequestMethod.GET)
    public ModelAndView get(@RequestParam String boardnumber, @RequestParam int curPage, @RequestParam String searchOption,
                            @RequestParam String keyword, HttpSession httpSession){
        System.out.println("get method called__________________________________");
        BoardVO tmpPost=boardService.get(boardnumber);
        boardService.increaseViews(tmpPost.getBoardnumber());
        ModelAndView modelAndView=new ModelAndView();
        httpSession.setAttribute("post",tmpPost);
        httpSession.setAttribute("boardnumber",tmpPost.getBoardnumber());
        httpSession.setAttribute("count", tmpPost.getRecnt());
        httpSession.setAttribute("curPage", curPage);
        httpSession.setAttribute("searchOption", searchOption);
        httpSession.setAttribute("keyword", keyword);
        httpSession.setAttribute("boardUserId",tmpPost.getUserId());
        modelAndView.setViewName("board/read");

        return modelAndView;
    }

    @RequestMapping(value = "updateForm.do")
    public String updateForm(@ModelAttribute BoardVO board) throws Exception{
        return "/board/updateForm";
    }

    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public String update(@ModelAttribute BoardVO board, HttpSession session) throws Exception{
        board.setId((String) session.getAttribute("id"));
        System.out.println(board.toString());
        boardService.update(board);
        return "redirect:list.do";
    }

    @RequestMapping(value= "delete.do")
    public String delete (@RequestParam String boardnumber,HttpSession session){
        String realId=boardService.get(boardnumber).getUserId();
        //권한 확인...
        if(session.getAttribute("id").equals(realId)){
            System.out.println("delete controller");
            boardService.delete(boardnumber);
        }
        return "redirect:list.do";
    }

    @RequestMapping(value = "write.do", method = RequestMethod.GET)
    public String write(@ModelAttribute BoardVO board, @ModelAttribute MemberVO member, HttpSession httpSession){
        System.out.println("writeForm method called**********");
        return "/board/writeForm";
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String add(@ModelAttribute BoardVO board, HttpSession httpSession){
        board.setId((String) httpSession.getAttribute("id"));
        boardService.add(board);
        return "redirect:list.do";
    }

}
