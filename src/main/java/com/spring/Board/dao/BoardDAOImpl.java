package com.spring.Board.dao;

import com.spring.Board.dto.BoardPager;
import com.spring.Board.dto.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public List<BoardVO> list() {
        System.out.println("list dao");
        List<BoardVO> tmpList=sqlSession.selectList("listBoard");
        return tmpList;
    }

    public Map<String, Object> listAll(BoardPager boardPager, String searchOption, String keyword) {
        // 검색옵션, 키워드 맵에 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        // BETWEEN #{start}, #{end}에 입력될 값을 맵에
        map.put("start", boardPager.getPageBegin());
        map.put("PAGE_SCALE", boardPager.getPageScale());
        System.out.println(searchOption + keyword);
        List<BoardVO> tmpList=sqlSession.selectList("board.listAlls", map);
        Map<String,Object> tmpMap=new HashMap<String, Object>();
        if(tmpList!=null){
            for(BoardVO vo:tmpList){
                System.out.println(vo.toString());
                tmpMap.put(vo.getBoardnumber(),vo);
            }
        }
        return tmpMap;
    }

    public int countArticle(String searchOption, String keyword) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        return sqlSession.selectOne("board.countArticle", map);
    }

    public int countReply(String boardnumber) {
        sqlSession.update("reply.increaseBoardRecnt",boardnumber);
        return sqlSession.selectOne("board.countReply",boardnumber);
    }

    public void add(BoardVO board) {
        System.out.println("add dao");
        sqlSession.selectOne("addBoard",board);
    }

    public void delete(String boardNumber) {
        System.out.println("delete dao");
        sqlSession.selectOne("deleteBoard",boardNumber);
    }

    public void update(BoardVO board) {
        System.out.println("udpate dao");
        sqlSession.selectOne("updateBoard",board);
    }

    public BoardVO get(String boardNumber) {
        System.out.println("get dao");
        BoardVO post=(BoardVO)sqlSession.selectOne("getBoard",boardNumber);
        return post;
    }

    public void increaseViews(String boardNumber) {
        System.out.println("increaseViews dao");
        int views=Integer.parseInt((String) sqlSession.selectOne("getViews",boardNumber))+1;
        BoardVO board=new BoardVO();
        board.setBoardnumber(boardNumber);
        board.setViews(String.valueOf(views));
        sqlSession.selectOne("increaseViews",board);
    }

    public int numberOfPost() {
        return 0;
    }


}
