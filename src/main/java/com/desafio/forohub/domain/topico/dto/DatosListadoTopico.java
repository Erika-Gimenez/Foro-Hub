package com.desafio.forohub.domain.topico.dto;

import com.desafio.forohub.domain.topico.entity.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 LocalDateTime fechaDeCreacion,
                                 String curso,
                                 String autor){
public DatosListadoTopico(Topico topico){
    this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getCurso(), topico.getAutor().getNombre());
}
}
