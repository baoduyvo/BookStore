package org.voduybao.bookstorebackend.dtos;

import lombok.Getter;
import lombok.Setter;
import org.voduybao.bookstorebackend.tools.contants.AuthProviderEnum;

public class SocialAuthDto {
    @Setter
    @Getter
    public static class Request {
        private AuthProviderEnum authProvider;
        private String accessToken;
        Integer authType;
    }
}
