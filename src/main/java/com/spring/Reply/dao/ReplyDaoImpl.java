package com.spring.Reply.dao;

import com.spring.Reply.dto.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return Integer.parseInt((String) sqlSession.selectOne("reply.count",boardnumber));
    }

}
