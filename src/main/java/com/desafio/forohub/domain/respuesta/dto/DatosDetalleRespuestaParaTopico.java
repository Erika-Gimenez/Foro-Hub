package com.desafio.forohub.domain.respuesta.dto;

import com.desafio.forohub.domain.usuario.dto.DatosUsuarioIdNombre;

import java.time.LocalDateTime;

public record DatosDetalleRespuestaParaTopico(Long id,
                                              String mensaje,
                                              LocalDateTime fechaDeCreacion,
                                              DatosUsuarioIdNombre autor) {
}
