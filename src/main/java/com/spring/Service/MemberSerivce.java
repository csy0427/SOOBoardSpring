package com.spring.Service;

import com.spring.Member.dto.MemberVO;

import java.util.List;

public interface MemberSerivce {
    // 회원 목록
    public List<MemberVO> memberList();
    // 회원 입력
    public void insertMember(MemberVO vo);
    // 회원 정보 상세보기
    public MemberVO viewMember(String userid);
    // 회원삭제
    public void deleteMember(String userid);
    // 회원정보 수정
    public void updateMember(MemberVO vo);

}
