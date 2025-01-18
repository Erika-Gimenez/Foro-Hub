package com.desafio.forohub.domain.topico.entity;

import com.desafio.forohub.domain.respuesta.Respuesta;
import com.desafio.forohub.domain.topico.dto.DatosActualizarTopico;
import com.desafio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.desafio.forohub.domain.usuario.dto.DatosUsuario;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Table(name = "topicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "mensaje"})})
@Entity(name = "Topico")
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

    public Topico() {
    }

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaDeCreacion, String curso, Usuario autor, List<Respuesta> respuestas) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.curso = curso;
        this.autor = autor;
        this.respuestas = respuestas;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(id, topico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
