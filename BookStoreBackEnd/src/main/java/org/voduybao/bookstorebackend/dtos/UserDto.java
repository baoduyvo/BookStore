package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class UserDto {
    @Getter
    @Setter
    public static class UserCreatorRequest {
        @NotNull(message = "Vui Lòng Nhập UserName")
        private String username;

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
        private String password_hash;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private String id;
        private String username;
    }
}
