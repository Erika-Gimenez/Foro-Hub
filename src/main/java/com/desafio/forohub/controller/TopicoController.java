package com.desafio.forohub.controller;

import com.desafio.forohub.domain.service.TopicoService;
import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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




}
