package com.desafio.forohub.domain.topico.dto;

import com.desafio.forohub.domain.usuario.DatosUsuarioId;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   String nombreCurso,
                                   DatosUsuarioId autor) {
}
