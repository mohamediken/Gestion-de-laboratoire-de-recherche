package com.iken.Labo.repository;

import com.iken.Labo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member save(Member member);

    void deleteById(Long memberId);

    Member getById(Long memberId);

    List<Member> findAll();

    Member findByUsername(String username);

}
