package io.github.mateusnere.sbootexpsecurity.domain.repository;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

    Optional<Grupo> findByNome(String nome);
}
