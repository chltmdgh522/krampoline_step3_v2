package com.example.kakao.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;


public record MemberCreateReq (

        @NotBlank
        @Schema(description = "회원 전화번호", example = "01033715386")
        String phone
) {
}
