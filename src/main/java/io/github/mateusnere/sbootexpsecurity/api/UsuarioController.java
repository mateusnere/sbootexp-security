package io.github.mateusnere.sbootexpsecurity.api;

import io.github.mateusnere.sbootexpsecurity.domain.dto.CadastroUsuarioDTO;
import io.github.mateusnere.sbootexpsecurity.domain.entity.Usuario;
import io.github.mateusnere.sbootexpsecurity.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) {
        Usuario usuarioSalvo = usuarioService.salvar(cadastroUsuarioDTO.getUsuario(), cadastroUsuarioDTO.getRoles());
        return ResponseEntity.ok(usuarioSalvo);
    }
}
