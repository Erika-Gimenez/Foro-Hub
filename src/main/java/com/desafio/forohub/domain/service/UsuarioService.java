package com.desafio.forohub.domain.service;

import com.desafio.forohub.domain.usuario.DatosRegistroUsuario;
import com.desafio.forohub.domain.usuario.DatosRespuestaUsuario;
import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import com.desafio.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public DatosRespuestaUsuario registrarUsuario(DatosRegistroUsuario datos) {
        Usuario usuario = new Usuario(datos);
        usuario = usuarioRepository.save(usuario);
        return new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }

}
