package com.desafio.forohub.domain.usuario.dto;

import jakarta.validation.constraints.*;

public record DatosRegistroUsuario(@NotBlank(message = "El nombre es obligatorio")
                                   String nombre,
                                   @NotBlank(message = "El correo es obligatorio")
                                   @Email(message = "El correo no tiene un formato válido")
                                   String correoElectronico,
                                   @NotBlank(message = "La contraseña es obligatoria")
                                   String contrasenia) {
}
