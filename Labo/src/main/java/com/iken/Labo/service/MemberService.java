package com.iken.Labo.service;

import com.iken.Labo.model.Member;
import com.iken.Labo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;



    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member getById(Long memberId) {
        return memberRepository.getById(memberId);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemeberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);

    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);

    }


    public Member getMemberById(Long id) {
       return memberRepository.findById(id).orElse(null);
    }


    public Member loadUserByUsername(String username){
        return memberRepository.findByUsername(username);
    }
}
