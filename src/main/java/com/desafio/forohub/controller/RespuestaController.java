package com.desafio.forohub.controller;

import com.desafio.forohub.domain.respuesta.DatosActualizarRespuesta;
import com.desafio.forohub.domain.respuesta.DatosEnviadosClienteRespuesta;
import com.desafio.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.desafio.forohub.domain.service.RespuestaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DatosEnviadosClienteRespuesta> registrarRespuesta(@Valid @RequestBody DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosEnviadosClienteRespuesta respuesta = respuestaService.crearRespuesta(datosRegistroRespuesta);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosEnviadosClienteRespuesta> obtenerRespuestaPorId(@PathVariable Long id) {
        DatosEnviadosClienteRespuesta respuesta = respuestaService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosEnviadosClienteRespuesta> actualizarRespuesta(@PathVariable Long id,
                                                                             @Valid @RequestBody DatosActualizarRespuesta datosActualizarRespuesta) {
        DatosEnviadosClienteRespuesta respuesta = respuestaService.actualizarRespuesta(id, datosActualizarRespuesta);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }


}
