package com.desafio.forohub.domain.topico.dto;

import jakarta.validation.constraints.*;

public record DatosRegistroTopico(
                                   @NotBlank(message = "El título del tópico es obligatorio")
                                   @Size(max = 100, message = "El título no puede exceder los 100 caracteres")
                                   String titulo,
                                   @NotBlank(message = "El mensaje del tópico es obligatorio")
                                   @Size(max = 1000, message = "El mensaje no puede exceder los 1000 caracteres")
                                   String mensaje,
                                   @NotBlank(message = "El curso es obligatorio")
                                   String curso,
                                   @NotNull(message = "El ID del autor es obligatorio")
                                   Long autorId
                                   ) {
}
