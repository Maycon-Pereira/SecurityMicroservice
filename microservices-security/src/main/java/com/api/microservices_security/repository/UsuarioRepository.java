package com.api.microservices_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.microservices_security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Optional<Usuario> findById(String id);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Optional<Usuario> findByEmail(String email);

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	UserDetails findByUsername(String email);


}
