package com.desafio.forohub.domain.topico.repository;

import com.desafio.forohub.domain.topico.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t ORDER BY t.fechaDeCreacion ASC")
    Page<Topico> findAllOrderedByFecha(Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);

    boolean existsByTituloAndAutorId(String titulo, Long autorId);

    long countByAutorId(Long autorId);

}
