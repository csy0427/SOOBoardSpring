package com.spring.Board.dao;

import com.spring.Board.dto.BoardPager;
import com.spring.Board.dto.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    List<BoardVO> list();

    void add(BoardVO board);

    void delete(String boardNumber);

    void update(BoardVO board);

    BoardVO get(String boardNumber);

    void increaseViews(String boardNumber);

    public int numberOfPost();

    public Map<String, Object> listAll(BoardPager boardPager, String searchOption, String keyword);

    int countArticle(String searchOption, String keyword);

    int countReply(String boardnumber);
}
