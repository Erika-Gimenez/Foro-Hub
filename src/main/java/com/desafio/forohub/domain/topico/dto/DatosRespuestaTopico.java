package com.desafio.forohub.domain.topico.dto;

import com.desafio.forohub.domain.usuario.dto.DatosRespuestaUsuario;

import java.time.LocalDateTime;


public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaDeCreacion,
                                   String nombreCurso,
                                   DatosRespuestaUsuario autor) {
}
