package com.spring.Service;

import com.spring.Member.dto.MemberVO;

import javax.servlet.http.HttpSession;

public interface MemberLoginService {
    public boolean loginCheck(HttpSession session, MemberVO vo);
    public void logout(HttpSession session);
}
