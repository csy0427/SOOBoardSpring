package com.spring.Reply.dao;

import com.spring.Reply.dto.ReplyPager;
import com.spring.Reply.dto.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReplyDaoImpl implements ReplyDao{

    @Autowired
    private SqlSession sqlSession;

    public List<ReplyVO> list(String boardnumber) {
        return sqlSession.selectList("reply.listReply",boardnumber);
    }

    public void create(ReplyVO vo) {
        sqlSession.insert("reply.insertReply",vo);
    }

    public void update(ReplyVO vo) {
        sqlSession.update("reply.updateReply",vo);
    }

    public void delete(String rno) {
        sqlSession.update("reply.deleteReply",rno);
    }

    public int count(String boardnumber) {
        System.out.println(boardnumber);
        return sqlSession.selectOne("reply.countReply",boardnumber);
    }

    public List<ReplyVO> listAll(ReplyPager replyPager, String boardnumber) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", replyPager.getPageBegin());
        map.put("PAGE_SCALE", replyPager.getPageScale());
        return sqlSession.selectList("reply.listAll", map);
        //return sqlSession.selectList("reply.listAll",boardnumber);
    }

    public ReplyVO detail(String rno) {
        return sqlSession.selectOne("reply.detailReply",rno);
    }

}
