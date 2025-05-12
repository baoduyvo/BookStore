-- Bảng lưu trữ các vai trò của người dùng
CREATE TABLE roles
(
    role_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name   VARCHAR(50) UNIQUE NOT NULL,
    description TEXT
) ENGINE = InnoDB;

INSERT INTO roles (role_name, description)
VALUES ('admin', 'admin');
INSERT INTO roles (role_name, description)
VALUES ('customer', 'customer');

-- Bảng lưu trữ các quyền hạn
CREATE TABLE permissions
(
    permission_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    permission_name VARCHAR(100) UNIQUE NOT NULL,
    description     TEXT
) ENGINE = InnoDB;

INSERT INTO permissions (permission_name, description)
VALUES ('admin', 'admin');
INSERT INTO permissions (permission_name, description)
VALUES ('customer', 'customer');

-- Bảng liên kết người dùng với vai trò
CREATE TABLE user_roles
(
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id)
) ENGINE = InnoDB;

-- Bảng liên kết vai trò với quyền hạn
CREATE TABLE role_permissions
(
    role_id       BIGINT,
    permission_id BIGINT,
    PRIMARY KEY (role_id, permission_id)
) ENGINE = InnoDB;

-- Ràng buộc khóa ngoại cho user_roles
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE;

-- Ràng buộc khóa ngoại cho role_permissions
ALTER TABLE role_permissions
    ADD CONSTRAINT fk_role_perm_role
        FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE;

ALTER TABLE role_permissions
    ADD CONSTRAINT fk_role_perm_perm
        FOREIGN KEY (permission_id) REFERENCES permissions (permission_id) ON DELETE CASCADE;

-- Gán quyền mặc định cho vai trò
INSERT INTO role_permissions (role_id, permission_id)
VALUES (1, 1);
INSERT INTO role_permissions (role_id, permission_id)
VALUES (2, 2);
