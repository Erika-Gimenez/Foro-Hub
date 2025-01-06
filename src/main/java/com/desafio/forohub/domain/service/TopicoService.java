package com.desafio.forohub.domain.service;

import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import com.desafio.forohub.domain.usuario.DatosUsuario;
import com.desafio.forohub.domain.usuario.DatosUsuarioId;
import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import com.desafio.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public TopicoService(ITopicoRepository topicoRepository, IUsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos) {
        Usuario usuario = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Topico topico = new Topico(datos,new DatosUsuario(usuario));
        topico = topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(), new DatosUsuarioId(usuario));
    }
}
