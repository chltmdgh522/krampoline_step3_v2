package com.example.kakao.member.service;

import com.example.kakao.global.dto.response.JwtTokenSet;
import com.example.kakao.global.dto.response.result.SingleResult;
import com.example.kakao.global.exception.CustomException;
import com.example.kakao.global.exception.ErrorCode;
import com.example.kakao.global.service.AuthService;
import com.example.kakao.global.service.ResponseService;
import com.example.kakao.member.dto.request.MemberCreateReq;
import com.example.kakao.member.dto.request.MemberLoginReq;
import com.example.kakao.member.entity.Member;
import com.example.kakao.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthService authService;

//    public SingleResult<JwtTokenSet> register(MemberCreateReq req) {
//        // 폰 중복 체크
//        if (memberRepository.existByPhone(req.phone())) {
//            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
//        }
//        Member newMember = Member.builder()
//                .memberId(UUID.randomUUID().toString())
//                .phone(req.phone())
//                .name(req.name())
//                .build();
//        newMember = memberRepository.save(newMember);
//
//        JwtTokenSet jwtTokenSet = authService.generateToken(newMember.getMemberId());
//
//        return ResponseService.getSingleResult(jwtTokenSet);
//    }

    public SingleResult<JwtTokenSet> login(MemberLoginReq req) {
        Optional<Member> findMember = memberRepository.findByPhone(req.phone());
        if(findMember.isEmpty()){
            Member newMember = Member.builder()
                    .memberId(UUID.randomUUID().toString())
                    .phone(req.phone())
                    .build();
            newMember = memberRepository.save(newMember);
            JwtTokenSet jwtTokenSet = authService.generateToken(newMember.getMemberId());

            return ResponseService.getSingleResult(jwtTokenSet);
        }

        Member member =findMember.get();

        JwtTokenSet jwtTokenSet = authService.generateToken(member.getMemberId());

        return ResponseService.getSingleResult(jwtTokenSet);
    }
}
