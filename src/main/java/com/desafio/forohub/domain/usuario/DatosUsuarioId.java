package com.desafio.forohub.domain.usuario;

public record DatosUsuarioId(Long Id) {
    public DatosUsuarioId(Usuario usuario) {
        this(usuario.getId());
    }
}

