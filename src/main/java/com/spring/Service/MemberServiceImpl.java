package com.spring.Service;

import com.spring.Member.dao.MemberDAO;
import com.spring.Member.dto.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberSerivce {

    @Autowired
    MemberDAO dao;

    public List<MemberVO> memberList() {
       return dao.memberList();
    }

    public void insertMember(MemberVO vo) {
        dao.insertMember(vo);
    }

    public MemberVO viewMember(String userid) {
        return dao.viewMember(userid);
    }

    public void deleteMember(String userid) {
        dao.deleteMember(userid);
    }

    public void updateMember(MemberVO vo) {
        dao.updateMember(vo);
    }
}
