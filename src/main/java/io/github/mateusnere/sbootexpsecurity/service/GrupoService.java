package io.github.mateusnere.sbootexpsecurity.service;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Grupo;
import io.github.mateusnere.sbootexpsecurity.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }
}
