-- Tạo bảng users
CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY,                    -- Khóa chính tự tăng
    email         VARCHAR(255) UNIQUE,                   -- Email, có thể NULL nếu đăng ký bằng số điện thoại
    phone_number  VARCHAR(20) UNIQUE,                    -- Số điện thoại, có thể NULL nếu đăng ký bằng email
    password_hash VARCHAR(255),                          -- Mật khẩu đã mã hóa
    auth_provider VARCHAR(50),                           -- Loại nhà cung cấp: 'facebook', 'google', 'tiktok'
    provider_id   VARCHAR(255),                          -- ID của người dùng trên nhà cung cấp
    is_status     VARCHAR(50) DEFAULT 'ACTIVE',          -- Trạng thái hoạt động của tài khoản
    is_verified   BOOLEAN     DEFAULT FALSE,             -- Trạng thái xác thực
    created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    updated_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP  -- Thời gian cập nhật
);


-- Tạo bảng tokens (chưa thêm khóa ngoại)
CREATE TABLE tokens
(
    user_id       BIGINT PRIMARY KEY,                  -- Khóa chính đồng thời là khóa ngoại
    access_token  VARCHAR(255) UNIQUE,                 -- Tạo Access Token của user
    refresh_token VARCHAR(255) UNIQUE,                 -- Tạo Refresh Token của user
    expires_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian hết hạn token
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Thời gian tạo
);

-- Tạo bảng user_profiles (chưa thêm khóa ngoại)
CREATE TABLE user_profiles
(
    user_id    BIGINT PRIMARY KEY, -- Khóa chính đồng thời là khóa ngoại
    full_name  VARCHAR(255),
    avatar_url VARCHAR(512),
    bio        TEXT,
    gender     VARCHAR(10),
    birthday   DATE,
    location   VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm ràng buộc khóa ngoại cho bảng tokens
ALTER TABLE tokens
    ADD CONSTRAINT fk_user_token
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
            ON DELETE CASCADE;

-- Thêm ràng buộc khóa ngoại cho bảng user_profiles
ALTER TABLE user_profiles
    ADD CONSTRAINT fk_user_profile
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
            ON DELETE CASCADE;


CREATE TABLE tokens
(
    token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token    VARCHAR(255) NOT NULL,
    expired  BOOLEAN DEFAULT FALSE,
    revoked  BOOLEAN DEFAULT FALSE,
    user_id  BIGINT UNIQUE, -- Liên kết 1-1: mỗi user chỉ có 1 token

    CONSTRAINT fk_token_user FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE
);


# 0123456789
# +84123456789
# +44 1234567890
# +1234567890
# user@example.com
# john.doe123@subdomain.example.co.uk
# email@domain.com