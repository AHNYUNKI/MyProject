package com.api.TopicTraverse.service.member;

import com.api.TopicTraverse.domain.member.Member;
import com.api.TopicTraverse.repository.member.MemberRepository;
import com.api.TopicTraverse.request.member.MemberSave;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    public void save(MemberSave memberSave) {

        Member member = Member.builder()
                .email(memberSave.getEmail())
                .password(memberSave.getPassword())
                .name(memberSave.getName())
                .build();

        memberRepository.save(member);

    }
}
