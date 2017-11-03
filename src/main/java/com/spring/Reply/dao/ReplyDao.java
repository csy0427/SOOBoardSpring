package com.spring.Reply.dao;

import com.spring.Reply.dto.ReplyVO;

import java.util.List;

public interface ReplyDao {

    List<ReplyVO> list(String boardnumber);

    void create(ReplyVO vo);

    void update(ReplyVO vo);

    void delete(String rno);

    int count(String boardnumber);
}
