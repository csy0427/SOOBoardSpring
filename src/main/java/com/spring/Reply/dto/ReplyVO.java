package com.spring.Reply.dto;

public class ReplyVO {
    private String  rno;        // 댓글 번호
    private String boardnumber;   // 게시글 번호
    private String replytext;    // 댓글 내용
    private String userid;        // 댓글 작성자

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getBno() {
        return boardnumber;
    }

    public void setBno(String boardnumber) {
        this.boardnumber = boardnumber;
    }

    public String getReplytext() {
        return replytext;
    }

    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

}
