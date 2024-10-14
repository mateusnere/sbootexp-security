package io.github.mateusnere.sbootexpsecurity.config;

import io.github.mateusnere.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.mateusnere.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var user = authentication.getName();
        var password = (String) authentication.getCredentials();

        var userMaster = "master";
        var passMaster = "@321";

        if(userMaster.equals(user) && passMaster.equals(password)) {

            var identificacaoUsuario = new IdentificacaoUsuario(
                    "user-master-id",
                    "master",
                    "Usuário Mestre",
                    List.of("ADMIN"));

            return new CustomAuthentication(identificacaoUsuario);

//            return new UsernamePasswordAuthenticationToken(
//                    "Usuário Mestre",
//                    null,
//                    List.of(new SimpleGrantedAuthority("ADMIN"))
//            );
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
