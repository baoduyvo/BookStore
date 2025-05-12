package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class AuthenDto {
    @Getter
    @Setter
    public static class RegisterRequest {
        @NotNull(message = "Số Điện Thoại Của Bạn Là Gì?")
        private String phone;

        @NotNull(message = "Vui Lòng Nhập Email")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Vui lòng nhập địa chỉ email hợp lệ."
        )
        private String email;

        @NotNull(message = "Vui Lòng Nhập Password")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{10,}$",
                message = "Mật khẩu phải chứa ít nhất một chữ số, một chữ cái thường, một chữ cái viết hoa và dài ít nhất 10 ký tự."
        )
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        @NotNull(message = "Vui Lòng Nhập Số Điện Thoại Hoặc Email")
        private String phoneOrEmail;

        @NotNull(message = "Vui Lòng Nhập Password")
        private String password;

        public boolean isPhone() {
            return phoneOrEmail.matches("^(\\+?[0-9]{1,4})?([0-9]{10})$");
        }

        public boolean isEmail() {
            return phoneOrEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;
        private Integer userId;
    }

    @Getter
    public static class LogoutRequest {
        @NotBlank(message = "Token cannot be null")
        private String accessToken;
    }
}