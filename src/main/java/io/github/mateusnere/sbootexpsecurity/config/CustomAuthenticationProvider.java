package io.github.mateusnere.sbootexpsecurity.config;

import io.github.mateusnere.sbootexpsecurity.domain.entity.Usuario;
import io.github.mateusnere.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.mateusnere.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import io.github.mateusnere.sbootexpsecurity.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senha = (String) authentication.getCredentials();

        Usuario usuarioComPermissoes = usuarioService.getUsuarioComPermissoes(login);
        if(usuarioComPermissoes != null) {
            boolean senhaValida = passwordEncoder.matches(senha, usuarioComPermissoes.getSenha());
            if(senhaValida) {
                IdentificacaoUsuario identificacaoUsuario =
                        new IdentificacaoUsuario(
                                usuarioComPermissoes.getId(),
                                usuarioComPermissoes.getLogin(),
                                usuarioComPermissoes.getNome(),
                                usuarioComPermissoes.getRoles());

                return new CustomAuthentication(identificacaoUsuario);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
