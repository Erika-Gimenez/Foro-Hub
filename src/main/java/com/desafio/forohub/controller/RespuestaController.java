package com.desafio.forohub.controller;

import com.desafio.forohub.domain.respuesta.dto.DatosActualizarRespuesta;
import com.desafio.forohub.domain.respuesta.dto.DatosDeRespuestaClienteRespuesta;
import com.desafio.forohub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.desafio.forohub.domain.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DatosDeRespuestaClienteRespuesta> registrarRespuesta(@Valid @RequestBody DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosDeRespuestaClienteRespuesta respuesta = respuestaService.crearRespuesta(datosRegistroRespuesta);
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
