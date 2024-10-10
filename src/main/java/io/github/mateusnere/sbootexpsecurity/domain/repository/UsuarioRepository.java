package io.github.mateusnere.sbootexpsecurity.domain.repository;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
