package io.github.mateusnere.sbootexpsecurity.domain.dto;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {

    private Usuario usuario;
    private List<String> roles;
}
