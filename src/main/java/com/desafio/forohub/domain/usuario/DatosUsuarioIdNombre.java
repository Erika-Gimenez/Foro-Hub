package com.desafio.forohub.domain.usuario;

public record DatosUsuarioIdNombre(Long id, String nombre) {
    public DatosUsuarioIdNombre(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
