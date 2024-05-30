package com.server.PracticeJpa.api.member.controller;

import com.server.PracticeJpa.api.member.dto.request.MemberSaveProfileDto;
import com.server.PracticeJpa.api.member.dto.response.MemberGetProfileResponseDto;
import com.server.PracticeJpa.api.member.service.MemberService;
import com.server.PracticeJpa.common.response.ApiResponse;
import com.server.PracticeJpa.common.response.SuccessStatus;
import com.server.PracticeJpa.common.util.MemberUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Member API", description = "Member API 입니다.")
public class MemberController {
    private final MemberService memberService;

    // 특정 Member 정보 조회
    @Tag(name = "Member API")
    @Operation(summary = "특정 Member 조회", description = "특정 Member를 memberId로 조회합니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberGetProfileResponseDto>> getMemberProfile(Principal principal, @PathVariable(name = "memberId") Long memberId) {
        System.out.println("MemberUtil.getMemberOAuth2Id(principal) = " + MemberUtil.getMemberId(principal));
        return ApiResponse.success(SuccessStatus.GET_PROFILE_SUCCESS, memberService.getMemberProfile(memberId));
    }

    // Member 유저 프로필 등록
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Object>> saveMemberProfile(Principal principal, @Valid @RequestBody MemberSaveProfileDto memberSaveProfileDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        memberService.saveMemberProfile(memberId, memberSaveProfileDto);
        return ApiResponse.success(SuccessStatus.SAVE_MEMBER_PROFILE);
    }
}
