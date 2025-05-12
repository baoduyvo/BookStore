package org.voduybao.bookstorebackend.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.voduybao.bookstorebackend.tools.contants.AuthProviderEnum;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Size(max = 20)
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 255)
    @Column(name = "password_hash")
    private String password;

    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    private AuthProviderEnum authProvider;

    @Size(max = 255)
    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "is_status")
    private Boolean isStatus;

    @Column(name = "is_verified")
    private Boolean isVerified;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void handleBeforeCreate() {
        this.isStatus = false;
        this.isVerified = false;
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
    }

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Token token;
}
