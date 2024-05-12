package com.server.InvestiMate.common.util;

import com.server.InvestiMate.common.exception.UnAuthorizedException;
import com.server.InvestiMate.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class MemberUtil {
    public static String getMemberOAuth2Id(Principal principal) {
        if (principal == null) {
            throw new UnAuthorizedException(ErrorStatus.INVALID_MEMBER.getMessage());
        }
        return principal.getName();
    }
}
