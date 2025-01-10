package com.desafio.forohub.domain.service;

import com.desafio.forohub.domain.topico.dto.DatosActualizarTopico;
import com.desafio.forohub.domain.topico.dto.DatosListadoTopico;
import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import com.desafio.forohub.domain.usuario.DatosUsuario;
import com.desafio.forohub.domain.usuario.DatosUsuarioId;
import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public Page<DatosListadoTopico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllOrderedByFecha(pageable).map(DatosListadoTopico::new);
    }

    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarInformaciones(datosActualizarTopico);

        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                new DatosUsuarioId(topico.getAutor().getId())
        );

        return ResponseEntity.ok(respuesta);
    }

}
