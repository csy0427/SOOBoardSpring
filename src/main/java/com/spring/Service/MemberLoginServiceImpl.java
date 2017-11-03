package com.spring.Service;

import com.spring.Member.dao.MemberDAO;
import com.spring.Member.dto.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class MemberLoginServiceImpl implements MemberLoginService{

    @Autowired
    MemberDAO dao;

    public boolean loginCheck(HttpSession session, MemberVO vo) {
        System.out.println("loginCheck service");
        boolean result=dao.loginCheck(vo);
        if(result){
            MemberVO vo2=viewMember(vo.getUserId());
            session.setAttribute("userid",vo2.getUserId());
            session.setAttribute("userpw",vo2.getUserpw());
        }
        return result;
    }

    public MemberVO viewMember(String userid) {
        return dao.viewMember(userid);
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
