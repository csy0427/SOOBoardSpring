package com.spring.Reply.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="reply")
public class ReplyVO {

    @Id
    @Column(name="rno")
    private String  rno;

    @Column(name="boardnumber")
    private String boardnumber;

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno='" + rno + '\'' +
                ", boardnumber='" + boardnumber + '\'' +
                ", replytext='" + replytext + '\'' +
                ", userid='" + userid + '\'' +
                ", secretreply='" + secretreply + '\'' +
                ", replyer='" + replyer + '\'' +
                '}';
    }

    @Column(name="replytext")
    private String replytext;

    @Column(name="userid")
    private String userid;

    @Column(name="secretreply")
    private String secretreply;

    @Column(name="replyer")
    private String replyer;


    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getBoardnumber() {
        return boardnumber;
    }

    public void setBoardnumber(String boardnumber) {
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

    public String getSecretreply() {
        return secretreply;
    }

    public void setSecretreply(String secretreply) {
        this.secretreply = secretreply;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
