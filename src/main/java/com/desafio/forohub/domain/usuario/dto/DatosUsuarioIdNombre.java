package com.desafio.forohub.domain.usuario.dto;

import com.desafio.forohub.domain.usuario.Usuario;

public record DatosUsuarioIdNombre(Long id, String nombre) {
    public DatosUsuarioIdNombre(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
