package com.spring.Board.dto;

import javax.persistence.*;

@Entity
@Table(name="board")
public class BoardVO {

    @Id
    @GeneratedValue
    @Column(name="boardnumber")
    private String boardnumber;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="userid", nullable=false)
    private String userid;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="views", nullable=false)
    private String views;

    @Column(name="show")
    private String show;

    @Column(name="recnt")
    private int recnt;

    public String getBoardnumber() {
        return boardnumber;
    }

    public void setBoardnumber(String boardnumber) {
        this.boardnumber = boardnumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userid;
    }

    public void setId(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }


    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getRecnt() {
        return recnt;
    }

    public void setRecnt(int recnt) {
        this.recnt = recnt;
    }
    @Override
    public String toString(){
        return "Board [boardnumber=" + getBoardnumber() + ", title=" + getTitle() +", content="+getContent() + ", id= "+ getUserId()+"]";
    }
    public boolean isSame(BoardVO vo){
        return vo.getBoardnumber()==this.getBoardnumber();
    }
}
