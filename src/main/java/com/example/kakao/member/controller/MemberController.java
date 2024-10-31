package com.example.kakao.member.controller;

import com.example.kakao.global.dto.response.JwtTokenSet;
import com.example.kakao.global.dto.response.SuccessResponse;
import com.example.kakao.global.dto.response.result.SingleResult;
import com.example.kakao.member.dto.request.MemberCreateReq;
import com.example.kakao.member.dto.request.MemberLoginReq;
import com.example.kakao.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(Member)")
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;



    @PostMapping("/login")
    @Operation(summary = "로그인 && 회원가입(자동 로그인)")
    public SuccessResponse<SingleResult<JwtTokenSet>> login(@Valid @RequestBody MemberLoginReq req) {
        SingleResult<JwtTokenSet> result = memberService.login(req);
        return SuccessResponse.ok(result);
    }
}
