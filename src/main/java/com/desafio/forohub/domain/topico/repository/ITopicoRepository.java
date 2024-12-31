package com.desafio.forohub.domain.topico.repository;

import com.desafio.forohub.domain.topico.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
}
