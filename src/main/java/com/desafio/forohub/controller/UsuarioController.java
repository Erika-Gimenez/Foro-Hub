package com.desafio.forohub.controller;

import com.desafio.forohub.domain.service.UsuarioService;
import com.desafio.forohub.domain.usuario.DatosRegistroUsuario;
import com.desafio.forohub.domain.usuario.DatosRespuestaUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@Valid @RequestBody DatosRegistroUsuario datos,
                                                                  UriComponentsBuilder uriBuilder) {
        DatosRespuestaUsuario respuesta = usuarioService.registrarUsuario(datos);
        URI location = uriBuilder.path("/usuario/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(location).body(respuesta);
    }

}
