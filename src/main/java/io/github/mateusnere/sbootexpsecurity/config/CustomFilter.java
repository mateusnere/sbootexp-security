package io.github.mateusnere.sbootexpsecurity.config;

import io.github.mateusnere.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.mateusnere.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerSecret = request.getHeader("x-secret");

        if(headerSecret != null && headerSecret.equals("secr3t")) {

            var identificacaoUsuario = new IdentificacaoUsuario(
                    "x-secret-id",
                    "x-secret",
                    "Usuário Secreto",
                    List.of("USER"));

//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    "Usuário Secreto",
//                    null,
//                    List.of(new SimpleGrantedAuthority("USER")));

            SecurityContext securityContextHolder = SecurityContextHolder.getContext();
            securityContextHolder.setAuthentication(new CustomAuthentication(identificacaoUsuario));
        }

        filterChain.doFilter(request, response);
    }
}
