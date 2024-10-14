package io.github.mateusnere.sbootexpsecurity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RHController {

    @PostMapping("/cadastro")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_RH')")
    public ResponseEntity<String> cadastroFake() {
        return ResponseEntity.ok("Autorizado");
    }
}
