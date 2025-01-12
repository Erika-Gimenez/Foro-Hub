package com.desafio.forohub.domain.topico.dto;

import com.desafio.forohub.domain.topico.entity.Topico;

public record DatosTopicoIdTitulo(Long id, String titulo) {
    public DatosTopicoIdTitulo(Topico topico) {
        this(topico.getId(), topico.getTitulo());
    }
}
