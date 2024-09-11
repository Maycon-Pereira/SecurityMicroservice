package com.api.microservices_security.domain;

public record DadosAtualizacaoUsuario(
    String username,
    String password,
    String email,
    String phoneNumber,
    Boolean twoFactorEnabled,
    Papel role
) {
}
