package com.desafio.forohub.domain.topico.dto;

import com.desafio.forohub.domain.respuesta.DatosDetalleRespuesta;
import com.desafio.forohub.domain.usuario.DatosUsuarioIdNombre;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetallesRespuestaTopico(Long id,
                                           String titulo,
                                           String mensaje,
                                           LocalDateTime fechaDeCreacion,
                                           String curso,
                                           DatosUsuarioIdNombre autor,
                                           List<DatosDetalleRespuesta> respuestas) {
}
