package com.api.microservices_security.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.microservices_security.domain.DadosAtualizacaoUsuario;
import com.api.microservices_security.domain.DadosDetalhamentoUsuario;
import com.api.microservices_security.domain.DadosRegistar;
import com.api.microservices_security.entity.Usuario;
import com.api.microservices_security.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosDetalhamentoUsuario criarUsuario(@Valid DadosRegistar dadosUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setUsername(dadosUsuario.username());
        usuario.setPassword(passwordEncoder.encode(dadosUsuario.password()));
        usuario.setEmail(dadosUsuario.email());
        usuario.setEnabled(true);
        usuario.setAccountNonExpired(true);
        usuario.setCredentialsNonExpired(true);
        usuario.setAccountNonLocked(true);
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());
        usuario.setPhoneNumber(dadosUsuario.phoneNumber());
        usuario.setTwoFactorEnabled(dadosUsuario.twoFactorEnabled());
        usuario.setRole(dadosUsuario.role());

        Usuario saved = usuarioRepository.save(usuario);

        return new DadosDetalhamentoUsuario(saved);
    }

    public DadosDetalhamentoUsuario atualizarInformacoes(String id, @Valid DadosAtualizacaoUsuario dados) throws Exception {
        Optional<Usuario> procurado = usuarioRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id do usuário não encontrado na base");
        }

        Usuario user = procurado.get();
        user.setUsername(dados.username());
        if (dados.password() != null && !dados.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dados.password()));
        }
        user.setEmail(dados.email());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPhoneNumber(dados.phoneNumber());
        user.setTwoFactorEnabled(dados.twoFactorEnabled());
        // Atualize o papel se necessário
        user.setRole(dados.role());

        Usuario saved = usuarioRepository.save(user);

        return new DadosDetalhamentoUsuario(saved);
    }

    public DadosDetalhamentoUsuario excluirUsuario(String id) throws Exception {
        Optional<Usuario> procurado = usuarioRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id do usuário não encontrado na base");
        }

        Usuario usuario = procurado.get();
        usuario.setEnabled(false);

        Usuario saved = usuarioRepository.save(usuario);

        return new DadosDetalhamentoUsuario(saved);
    }

    public DadosDetalhamentoUsuario detalharUsuario(String id) throws Exception {
    	System.out.println("começo! ");
        Optional<Usuario> procurado = usuarioRepository.findById(id);

        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id do usuário não encontrado na base");
        }
        
        System.out.println("foi detalhar! ");

        Usuario usuario = procurado.get();
        return new DadosDetalhamentoUsuario(usuario);
    }
}
