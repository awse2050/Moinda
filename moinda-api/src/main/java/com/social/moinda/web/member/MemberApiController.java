package com.social.moinda.web.member;

import com.social.moinda.api.member.dto.SignupRequest;
import com.social.moinda.api.member.service.MemberCommandService;
import com.social.moinda.api.member.service.MemberQueryService;
import com.social.moinda.core.domains.member.dto.MemberDetails;
import com.social.moinda.core.domains.member.dto.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Validated SignupRequest dto) {

        SignupResponse SignupResponse = memberCommandService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(SignupResponse);
    }

    // TODO : Test Method, convert memberId to Member(AuthenticationPrincipal)
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDetails> getMemberDetails(@PathVariable Long memberId) {
        MemberDetails memberDetails = memberQueryService.getMemberWithGroupInfo(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(memberDetails);
    }
}
