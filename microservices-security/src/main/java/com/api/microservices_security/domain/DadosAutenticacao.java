package com.api.microservices_security.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
		
		@Email
		@NotBlank
		String email,
		@NotBlank
		String password) {

}
