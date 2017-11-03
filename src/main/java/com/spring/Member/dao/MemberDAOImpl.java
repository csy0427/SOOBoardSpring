package com.spring.Member.dao;

import com.spring.Member.dto.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSessionTemplate sqlSession;

    public List<MemberVO> memberList() {
        List<MemberVO> memberList=sqlSession.selectList("list");
        return memberList;
    }

    public void insertMember(MemberVO vo) {
        sqlSession.selectOne("insertMember",vo);
    }

    public MemberVO viewMember(String userid) {
        return (MemberVO) sqlSession.selectOne("getMember",userid);
    }

    public void deleteMember(String userid) {
        sqlSession.selectOne("delete",userid);
    }

    public void updateMember(MemberVO vo) {
        sqlSession.selectOne("update",vo);
    }

    public boolean loginCheck(MemberVO vo) {
        System.out.println("loginCheck dao");
        String name= (String) sqlSession.selectOne("loginCheck",vo);
        System.out.println("loginCheckDao"+name);
        return name==null? false:true;
    }

}
