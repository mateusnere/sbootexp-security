package io.github.mateusnere.sbootexpsecurity.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificacaoUsuario {

    private String id;
    private String login;
    private String nome;
    private List<String> roles;

    public List<String> getRoles() {
        if(roles == null) {
            return new ArrayList<>();
        }

        return roles;
    }
}
