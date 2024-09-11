package com.api.microservices_security.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservices_security.domain.DadosAtualizacaoUsuario;
import com.api.microservices_security.domain.DadosDetalhamentoUsuario;
import com.api.microservices_security.service.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
	
	 @GetMapping("/{id}")
		public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable String id) throws Exception {
			
			DadosDetalhamentoUsuario response = usuarioService.detalharUsuario(id);
			
			return ResponseEntity.ok(response);
		}
	        
	    @PutMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoUsuario dados) throws Exception {

			DadosDetalhamentoUsuario response = usuarioService.atualizarInformacoes(id, dados);
			if (response == null) {
				throw new AccountNotFoundException("Id não encontrado na base");
			}
			return ResponseEntity.ok(response);
		}
		
		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoUsuario> excluir(@PathVariable String id) throws Exception {
			
			usuarioService.excluirUsuario(id);


			 return ResponseEntity.noContent().build();
		}
		
		
}
