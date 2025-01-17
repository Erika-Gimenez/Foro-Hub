package com.desafio.forohub.controller;

import com.desafio.forohub.domain.service.TopicoService;
import com.desafio.forohub.domain.topico.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico,  UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaTopico respuesta = topicoService.registrarTopico(datosRegistroTopico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesRespuestaTopico> obtenerTopico(@PathVariable Long id) {
        DatosDetallesRespuestaTopico respuestaTopico = topicoService.obtenerTopicoYRespuestaPorId(id);

        return ResponseEntity.ok(respuestaTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        DatosRespuestaTopico respuestaTopico = topicoService.actualizarTopico(id, datosActualizarTopico);

        return ResponseEntity.ok(respuestaTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
            topicoService.eliminarTopico(id);
            return ResponseEntity.noContent().build();

    }

}
