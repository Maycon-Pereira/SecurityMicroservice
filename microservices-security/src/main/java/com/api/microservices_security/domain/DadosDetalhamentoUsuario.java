package com.api.microservices_security.domain;

import java.time.LocalDateTime;

import com.api.microservices_security.entity.Usuario;

public record DadosDetalhamentoUsuario(
        String id,
        String username,
        String password,
        String email,
        Boolean enabled,
        Boolean accountNonExpired,
        Boolean credentialsNonExpired,
        Boolean accountNonLocked,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String phoneNumber,
        Boolean twoFactorEnabled,
        Papel roles) { // Troque 'Role' por 'Papel' se você estiver usando o enum Papel em vez de Role.

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getEmail(),
            usuario.isEnabled(),
            usuario.isAccountNonExpired(),
            usuario.isCredentialsNonExpired(),
            usuario.isAccountNonLocked(),
            usuario.getLastLogin(),
            usuario.getCreatedAt(),
            usuario.getUpdatedAt(),
            usuario.getPhoneNumber(),
            usuario.getTwoFactorEnabled(),
            usuario.getRole()
        );
    }
}
