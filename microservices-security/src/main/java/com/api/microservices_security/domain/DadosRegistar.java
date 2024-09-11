package com.api.microservices_security.domain;

public record DadosRegistar(
    String username,
    String password,
    String email,
    String phoneNumber,
    Boolean twoFactorEnabled,
    Papel role
) {
}
