package com.desafio.forohub.domain.service;

import com.desafio.forohub.domain.respuesta.dto.DatosDetalleRespuestaParaTopico;
import com.desafio.forohub.domain.topico.dto.*;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import com.desafio.forohub.domain.topico.validaciones.IValidacionTopico;
import com.desafio.forohub.domain.usuario.*;
import com.desafio.forohub.domain.usuario.dto.DatosRespuestaUsuario;
import com.desafio.forohub.domain.usuario.dto.DatosUsuario;
import com.desafio.forohub.domain.usuario.dto.DatosUsuarioIdNombre;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final List<IValidacionTopico> validadores;

    @Autowired
    public TopicoService(ITopicoRepository topicoRepository, IUsuarioRepository usuarioRepository, List<IValidacionTopico> validadores) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.validadores = validadores;
    }

    //post
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos) {
        validadores.forEach(v -> v.validar(datos));
        Usuario usuario = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Topico topico = new Topico(datos,new DatosUsuario(usuario));
        topico = topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaDeCreacion(), topico.getCurso(),
                new DatosRespuestaUsuario(topico.getAutor().getId(),
                        topico.getAutor().getNombre(),
                        topico.getAutor().getCorreoElectronico()));
    }

    //get list
    public Page<DatosListadoTopico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllOrderedByFecha(pageable).map(DatosListadoTopico::new);
    }

    public DatosDetallesRespuestaTopico obtenerTopicoYRespuestaPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + id));

        List<DatosDetalleRespuestaParaTopico> respuestas = topico.getRespuestas().stream()
                .map(respuesta -> new DatosDetalleRespuestaParaTopico(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getFechaDeCreacion(),
                        new DatosUsuarioIdNombre(
                                respuesta.getAutor().getId(),
                                respuesta.getAutor().getNombre()
                        )
                )).toList();

        return new DatosDetallesRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getCurso(),
                new DatosUsuarioIdNombre(topico.getAutor().getId(), topico.getAutor().getNombre()),
                respuestas
        );
    }

    //put
    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarInformaciones(datosActualizarTopico);

        return new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getCurso(),
                new DatosRespuestaUsuario(topico.getAutor().getId(),
                        topico.getAutor().getNombre(),
                        topico.getAutor().getCorreoElectronico())
        );

    }

    @Transactional
    public void eliminarTopico(Long id){
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Topico no encontrada");
        }
        topicoRepository.deleteById(id);
    }

  }
