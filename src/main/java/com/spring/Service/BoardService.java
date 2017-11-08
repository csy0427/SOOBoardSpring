package com.spring.Service;

import com.spring.Board.dto.BoardPager;
import com.spring.Board.dto.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardVO> list();
    BoardVO get(String boardNumber);
    void update(BoardVO board);
    void delete(String boardNumber);
    void add(BoardVO board);
    void increaseViews(String boardNumber);
    int countArticle(String searchOption, String keyword);
    Map<String, Object> listAll(BoardPager boardPager, String searchOption, String keyword);
    int countReply(String boardnumber);
}
