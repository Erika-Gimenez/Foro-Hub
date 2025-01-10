package com.desafio.forohub.domain.topico.entity;

import com.desafio.forohub.domain.respuesta.Respuesta;
import com.desafio.forohub.domain.topico.dto.DatosActualizarTopico;
import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.usuario.DatosUsuario;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(nullable = false)
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private String curso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public Topico (DatosRegistroTopico dto, DatosUsuario dtoUsuario){
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.curso = dto.curso();
        this.autor = dtoUsuario.usuario();
    }

    public void actualizarInformaciones(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null) {
            this.curso=datos.curso();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getCurso() {
        return curso;
    }

    public Usuario getAutor() {
        return autor;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }
}
