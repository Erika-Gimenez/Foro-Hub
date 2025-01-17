package com.desafio.forohub.domain.topico.validaciones;

import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.topico.repository.ITopicoRepository;
import com.desafio.forohub.infra.errores.ValidacionException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeLimiteDeTopicosPorUsuario implements IValidacionTopico{

    private final ITopicoRepository topicoRepository;
    private final int LIMITE_TOPICOS = 10;

    public ValidadorDeLimiteDeTopicosPorUsuario(ITopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }


    @Override
    public void validar(DatosRegistroTopico datos) {
        long cantidadTopicos = topicoRepository.countByAutorId(datos.autorId());
        if (cantidadTopicos >= LIMITE_TOPICOS) {
            throw new ValidacionException("Un autor no puede tener más de " + LIMITE_TOPICOS + " tópicos.");
        }
    }
}
