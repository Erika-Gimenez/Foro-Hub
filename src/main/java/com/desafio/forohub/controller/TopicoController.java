package com.desafio.forohub.controller;

import com.desafio.forohub.domain.service.TopicoService;
import com.desafio.forohub.domain.topico.dto.DatosActualizarTopico;
import com.desafio.forohub.domain.topico.dto.DatosListadoTopico;
import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.desafio.forohub.domain.topico.entity.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico) {
        DatosRespuestaTopico respuesta = topicoService.registrarTopico(datosRegistroTopico);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        return topicoService.actualizarTopico(id, datosActualizarTopico);
    }


}
