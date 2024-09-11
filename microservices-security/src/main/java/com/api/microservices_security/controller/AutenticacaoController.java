package com.api.microservices_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.microservices_security.domain.DadosAutenticacao;
import com.api.microservices_security.domain.DadosDetalhamentoUsuario;
import com.api.microservices_security.domain.DadosRegistar;
import com.api.microservices_security.domain.DadosTokenJWT;
import com.api.microservices_security.entity.Usuario;
import com.api.microservices_security.service.TokenService;
import com.api.microservices_security.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoUsuario> registrar(@RequestBody @Valid DadosRegistar dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.criarUsuario(dados);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dadosDetalhamentoUsuario.id()).toUri();

        return ResponseEntity.created(uri).body(dadosDetalhamentoUsuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
   
}
