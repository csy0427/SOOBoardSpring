package com.spring.Service;

import com.spring.Board.dao.BoardDAO;
import com.spring.Board.dto.BoardPager;
import com.spring.Board.dto.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements  BoardService{

    @Autowired
    private BoardDAO boardDAO;

    Calendar calendar;

    public List<BoardVO> list() {
        System.out.println("BoardService list");
        return boardDAO.list();
    }

    public List<BoardVO> listAll(BoardPager boardPager, String searchOption, String keyword){
        return boardDAO.listAll(boardPager, searchOption, keyword);
    }

    public int countReply(String boardnumber) {
        return boardDAO.countReply(boardnumber);
    }

    public BoardVO get(String boardNumber) {
        return boardDAO.get(boardNumber);
    }

    public void update(BoardVO board) {
        boardDAO.update(board);
    }

    public void delete(String boardNumber) {
        boardDAO.delete(boardNumber);
    }

    public void add(BoardVO board) {
        board.setBoardnumber(getBoardnumber());
        boardDAO.add(board);
    }

    public void increaseViews(String boardNumber) {
        boardDAO.increaseViews(boardNumber);
    }

    public int numberOfPost(){
        System.out.println("numberOfPost dao");
        int number=boardDAO.numberOfPost();
        return number;
    }
    public String getBoardnumber(){
        calendar= Calendar.getInstance();
        String boarnumber= String.valueOf(calendar.get(Calendar.YEAR))+ String.valueOf(calendar.get(Calendar.MONTH))+
                String.valueOf(calendar.get(Calendar.DATE))+String.valueOf(calendar.get(Calendar.MILLISECOND));
        return boarnumber;
    }

    public int countArticle(String searchOption, String keyword) {
        return boardDAO.countArticle(searchOption, keyword);
    }
}
