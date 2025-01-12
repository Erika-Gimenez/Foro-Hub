package com.desafio.forohub.domain.topico.dto;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,
                                    String titulo,
                                    String mensaje,
                                    String curso) {
}