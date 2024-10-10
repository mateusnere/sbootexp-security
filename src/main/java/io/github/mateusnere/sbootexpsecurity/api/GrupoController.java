package io.github.mateusnere.sbootexpsecurity.api;

import io.github.mateusnere.sbootexpsecurity.service.GrupoService;
import io.github.mateusnere.sbootexpsecurity.domain.entity.Grupo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
        Grupo grupoSalvo = grupoService.salvar(grupo);
        return ResponseEntity.ok(grupoSalvo);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Grupo>> listar() {
        List<Grupo> listaGrupos = grupoService.listar();
        return ResponseEntity.ok(listaGrupos);
    }
}
