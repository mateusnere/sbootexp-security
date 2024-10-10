package io.github.mateusnere.sbootexpsecurity.service;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Grupo;
import io.github.mateusnere.sbootexpsecurity.domain.entity.Usuario;
import io.github.mateusnere.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.mateusnere.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.mateusnere.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.mateusnere.sbootexpsecurity.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> roles) {

        List<UsuarioGrupo> usuarioGrupoList = roles.stream().map(nomeRole -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeRole);
                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }
                    return null;
                }).filter(Objects::nonNull)
                .toList();

        if(!usuarioGrupoList.isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuarioRepository.save(usuario);
            usuarioGrupoRepository.saveAll(usuarioGrupoList);
        }

        return usuario;
    }

    public Usuario getUsuarioComPermissoes(String login) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if(usuarioOptional.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOptional.get();
        List<String> rolesByUsuario = usuarioGrupoRepository.findRolesByUsuario(usuario);
        usuario.setRoles(rolesByUsuario);
        return usuario;
    }

}
