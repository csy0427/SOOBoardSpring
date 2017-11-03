package com.spring.Service;

import com.spring.Reply.dto.ReplyVO;

import java.util.List;

public interface ReplyService {
    // 댓글 목록
    public List<ReplyVO> list(String bno);
    // 댓글 입력
    public void create(ReplyVO vo);
    // 댓글 수정
    public void update(ReplyVO vo);
    // 댓글 삭제
    public void delete(String rno);

    int count(String boardnumber);

}
