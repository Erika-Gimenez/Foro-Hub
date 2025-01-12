package com.desafio.forohub.domain.respuesta;

import com.desafio.forohub.domain.usuario.DatosUsuarioIdNombre;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(Long id,
                                    String mensaje,
                                    LocalDateTime fechaDeCreacion,
                                    DatosUsuarioIdNombre autor) {
}
