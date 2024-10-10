package io.github.mateusnere.sbootexpsecurity.service;

import io.github.mateusnere.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.mateusnere.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioGrupoService {

    private final UsuarioGrupoRepository usuarioGrupoRepository;

    @Transactional
    public UsuarioGrupo salvar(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }
}
