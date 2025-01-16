package com.desafio.forohub.domain.service;

import com.desafio.forohub.domain.respuesta.*;
import com.desafio.forohub.domain.topico.dto.DatosTopico;
import com.desafio.forohub.domain.topico.dto.DatosTopicoIdTitulo;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import com.desafio.forohub.domain.usuario.DatosUsuario;
import com.desafio.forohub.domain.usuario.DatosUsuarioIdNombre;
import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    private final IRespuestaRepository respuestaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ITopicoRepository topicoRepository;

    public RespuestaService(IRespuestaRepository respuestaRepository, IUsuarioRepository usuarioRepository, ITopicoRepository topicoRepository) {
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
    }


    //post
    public DatosDeRespuestaClienteRespuesta crearRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        System.out.println("Datos recibidos: " + datosRegistroRespuesta);
        Usuario autor = usuarioRepository.findById(datosRegistroRespuesta.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        Topico topico = topicoRepository.findById(datosRegistroRespuesta.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        Respuesta respuesta = new Respuesta(datosRegistroRespuesta, new DatosTopico(topico), new DatosUsuario(autor));
        System.out.println("Respuesta antes de guardar: " + respuesta); System.out.println("Fecha de creación: " + respuesta.getFechaDeCreacion());
        respuestaRepository.save(respuesta);

        return new DatosDeRespuestaClienteRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaDeCreacion(), new DatosUsuarioIdNombre(autor), new DatosTopicoIdTitulo(topico), respuesta.isSolucion());
    }

    //get
    public DatosDeRespuestaClienteRespuesta obtenerRespuestaPorId(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));
        return new DatosDeRespuestaClienteRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaDeCreacion(),
                new DatosUsuarioIdNombre(respuesta.getAutor()),
                new DatosTopicoIdTitulo(respuesta.getTopico()),
                respuesta.isSolucion());
    }

    //put
    @Transactional
    public DatosDeRespuestaClienteRespuesta actualizarRespuesta(Long id, DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));
        respuesta.actualizarInformacion(datosActualizarRespuesta);
        return new DatosDeRespuestaClienteRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaDeCreacion(),
                new DatosUsuarioIdNombre(respuesta.getAutor()),
                new DatosTopicoIdTitulo(respuesta.getTopico()),
                respuesta.isSolucion());
    }

    //delete
    @Transactional
    public void eliminarRespuesta(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new EntityNotFoundException("Respuesta no encontrada");
        }
        respuestaRepository.deleteById(id);
    }



}
