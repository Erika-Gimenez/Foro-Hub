package com.desafio.forohub.domain.respuesta.dto;

import com.desafio.forohub.domain.topico.dto.DatosTopicoIdTitulo;
import com.desafio.forohub.domain.usuario.dto.DatosUsuarioIdNombre;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DatosDeRespuestaClienteRespuesta(@NotNull Long id,
                                               String mensaje,
                                               LocalDateTime fechaDeCreacion,
                                               DatosUsuarioIdNombre usuario,
                                               DatosTopicoIdTitulo topico,
                                               boolean solucion
                                     ) {
}
