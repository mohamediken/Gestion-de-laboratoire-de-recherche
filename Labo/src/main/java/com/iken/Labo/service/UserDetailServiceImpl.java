package com.iken.Labo.service;

import com.iken.Labo.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
    private MemberService memberService;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member=memberService.loadUserByUsername(username);
        if(member==null) throw new UsernameNotFoundException(String.format("Member %s not found",username));
        String[] roles = member.getRoles().stream().map(u -> u.getRolename()).toArray(String[]::new);

        UserDetails userDetails= User
                .withUsername(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(roles)
                .build();
        return userDetails;
    }

}

