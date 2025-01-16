package com.desafio.forohub.controller;

import com.desafio.forohub.domain.respuesta.DatosActualizarRespuesta;
import com.desafio.forohub.domain.respuesta.DatosDeRespuestaClienteRespuesta;
import com.desafio.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.desafio.forohub.domain.service.RespuestaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private static final Logger log = LoggerFactory.getLogger(RespuestaController.class);

    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DatosDeRespuestaClienteRespuesta> registrarRespuesta(@Valid @RequestBody DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosDeRespuestaClienteRespuesta respuesta = respuestaService.crearRespuesta(datosRegistroRespuesta);
        log.info("Datos recibidos para registrar respuesta: {}", datosRegistroRespuesta);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDeRespuestaClienteRespuesta> obtenerRespuestaPorId(@PathVariable Long id) {
        DatosDeRespuestaClienteRespuesta respuesta = respuestaService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDeRespuestaClienteRespuesta> actualizarRespuesta(@PathVariable Long id,
                                                                                @Valid @RequestBody DatosActualizarRespuesta datosActualizarRespuesta) {
        DatosDeRespuestaClienteRespuesta respuesta = respuestaService.actualizarRespuesta(id, datosActualizarRespuesta);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }


}
